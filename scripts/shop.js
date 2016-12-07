angular.module('shop', [])

  .config(function($routeProvider) {
    $routeProvider.
      when('/', {controller: ShopListCtrl, templateUrl: 'list_shop.html'}).
      when('/new_shop', {controller: CreateShopCtrl, templateUrl: 'edit_shop.html'}).
      when('/edit_shop/:shopId', {controller: EditShopCtrl, templateUrl: 'edit_shop.html'}).
      when('/destroy_shop/:shopId', {controller: DestroyShopCtrl, templateUrl: 'list_shop.html'}).
      when('/products/:shopId', {controller: ProductsCtrl, templateUrl: 'list_product.html'}).
      when('/new_product/:shopId', {controller: CreateProductCtrl, templateUrl: 'edit_product.html'}).
      when('/edit_product/:shopId/:productId', {controller: EditProductCtrl, templateUrl: 'edit_product.html'}).
      when('/destroy_product/:shopId/:productId', {controller: DestroyProductCtrl, templateUrl: 'list_product.html'}).
      otherwise({redirectTo:'/'});
  });

var serverURL = "https://shopmanagment.herokuapp.com/"

function init($scope, $rootScope, $http) {
  $http.get(serverURL+'shop').success(function(data) {
    $rootScope.shops = data;
  });
}

function ShopListCtrl($scope, $rootScope, $location, $http) {
  if (!$rootScope.shops) init($scope, $rootScope, $http)
}
 
function CreateShopCtrl($scope, $location, $http, $rootScope) {
  if (!$rootScope.shops) { $location.path('/'); return; };
  $scope.save = function() {
    $http.post('http://localhost/SOAPService/ShopService.svc/json/Shops?name='+$scope.shop.Name+'&time='+$scope.shop.Time+'&adress='+$scope.shop.Adress).success(function(data) {
      $rootScope.shops.push(data);
      $location.path('/');
    });
  };
}

function clone(obj){
    var temp = {}; 
    for(var key in obj)
        temp[key] = obj[key];
    return temp;
}

function EditShopCtrl($scope, $location, $rootScope, $routeParams, $http) {
  if (!$rootScope.shops) { $location.path('/'); return; };
  var index = $rootScope.shops.findIndex(function(el){ return el.Id == $routeParams.shopId });
  $scope.shop = clone($rootScope.shops[index]);

  $scope.save = function() {
    $http.put('http://localhost/SOAPService/ShopService.svc/json/Shops?id='+$scope.shop.Id+'&name='+$scope.shop.Name+'&time='+$scope.shop.Time+'&adress='+$scope.shop.Adress).success(function(data) {
      $rootScope.shops[index] = data;
      $location.path('/');
    });
  };
}

function DestroyShopCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  $http.delete('http://localhost/SOAPService/ShopService.svc/json/Shops?id='+$routeParams.shopId).success(function() {
    init($scope, $rootScope, $http)
    $location.path('/');
  });
}



function ProductsCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  var index = $rootScope.shops.findIndex(function(el){ return el.Id == $routeParams.shopId });
  $scope.shop = clone($rootScope.shops[index]);

  $http.get('http://localhost/SOAPService/ProductService.svc/json/Products?shopId='+$scope.shop.Id).success(function(data) {
    $rootScope.products = data;
  }); 

  //Maps
  var geocoder = new google.maps.Geocoder();
  geocoder.geocode( { 'address': $scope.shop.Adress}, 
    function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
          var pos = results[0].geometry.location;
          var map = new google.maps.Map(document.getElementById('map'), {
            mapTypeId: google.maps.MapTypeId.ROADMAP,
            zoom: 15
          });
          map.setCenter(results[0].geometry.location);
            var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });
      }
    }); 

}

function CreateProductCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  var index = $rootScope.shops.findIndex(function(el){ return el.Id == $routeParams.shopId });
  $scope.shop = clone($rootScope.shops[index]);

  $scope.save = function(){
    $http.post('http://localhost/SOAPService/ProductService.svc/json/Products?shopId='+$scope.shop.Id+'&name='+$scope.product.Name+'&description='+$scope.product.Description).success(function(data) {
      $rootScope.products.push(data);
      $location.path("/products/"+$scope.shop.Id);
    });
  }
}

function EditProductCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  $scope.shop = clone($rootScope.shops[$rootScope.shops.findIndex(function(el){ return el.Id == $routeParams.shopId })]);
  var index = $rootScope.products.findIndex(function(el){ return el.Id == $routeParams.productId });
  $scope.product = clone($rootScope.products[index]);

  $scope.save = function(){
    $http.put('http://localhost/SOAPService/ProductService.svc/json/Products?shopId='+$scope.shop.Id+'&id='+$scope.product.Id+'&name='+$scope.product.Name+'&description='+$scope.product.Description).success(function(data) {
      $rootScope.products[index] = data;
      $location.path("/products/"+$scope.shop.Id);
    });
  }
}

function DestroyProductCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  $http.delete('http://localhost/SOAPService/ProductService.svc/json/Products?shopId='+$routeParams.shopId+'&id='+$routeParams.productId).success(function() {
    $http.get('http://localhost/SOAPService/ProductService.svc/json/Products?shopId='+$routeParams.shopId).success(function(data) {
      $rootScope.products = data;
    });
    $location.path("/products/"+$routeParams.shopId);
  });
}