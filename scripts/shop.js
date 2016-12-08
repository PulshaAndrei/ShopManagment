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

var serverURL = "http://localhost:8080/"//https://shopmanagment.herokuapp.com/"

function ShopListCtrl($scope, $rootScope, $location, $http) {
  $http.get(serverURL+'shop').success(function(data) {
    $rootScope.shops = data["data"];
  });
}
 
function CreateShopCtrl($scope, $location, $http, $rootScope) {
  if (!$rootScope.shops) { $location.path('/'); return; };
  $scope.save = function() {
    var shop = {
      name: $scope.shop.name,
      time: $scope.shop.time,
      address: $scope.shop.address
    }
    $http.post(serverURL+'shop', shop).success(function(data) {
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
  var index = $rootScope.shops.findIndex(function(el){ return el.id == $routeParams.shopId });
  $scope.shop = clone($rootScope.shops[index]);

  $scope.save = function() {
    var shop = {
      name: $scope.shop.name,
      time: $scope.shop.time,
      address: $scope.shop.address
    }
    $http.put(serverURL+'shop/'+$scope.shop.id, shop).success(function(data) {
      $location.path('/');
    });
  };
}

function DestroyShopCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  $http.delete(serverURL+'shop/'+$routeParams.shopId).success(function() {
    $location.path('/');
  });
}



function ProductsCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  var index = $rootScope.shops.findIndex(function(el){ return el.id == $routeParams.shopId });
  $scope.shop = clone($rootScope.shops[index]);

  $http.get(serverURL+'product/'+$scope.shop.id).success(function(data) {
    $rootScope.products = data["data"];
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
  var index = $rootScope.shops.findIndex(function(el){ return el.id == $routeParams.shopId });
  $scope.shop = clone($rootScope.shops[index]);

  $scope.save = function(){
    var product = {
      name: $scope.product.name,
      description: $scope.product.description
    }
    $http.post(serverURL+'product/'+$scope.shop.id, product).success(function(data) {
      $location.path("/products/"+$scope.shop.id);
    });
  }
}

function EditProductCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  $scope.shop = clone($rootScope.shops[$rootScope.shops.findIndex(function(el){ return el.id == $routeParams.shopId })]);
  var index = $rootScope.products.findIndex(function(el){ return el.id == $routeParams.productId });
  $scope.product = clone($rootScope.products[index]);

  $scope.save = function(){
    var product = {
      name: $scope.product.name,
      description: $scope.product.description
    }
    $http.put(serverURL+'product/'+$scope.shop.id+'/'+$scope.product.id, product).success(function(data) {
      $location.path("/products/"+$scope.shop.id);
    });
  }
}

function DestroyProductCtrl($scope, $location, $rootScope, $routeParams, $http){
  if (!$rootScope.shops) { $location.path('/'); return; };
  $http.delete(serverURL+'product/'+$routeParams.shopId+'/'+$routeParams.productId).success(function() {
    $location.path("/products/"+$routeParams.shopId);
  });
}