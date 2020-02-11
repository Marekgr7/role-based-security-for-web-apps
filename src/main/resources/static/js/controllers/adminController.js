// var homeCtrl = angular.module('homeCtrl', ['ngRoute'] );
//
// homeCtrl.controller('home',['$scope', '$filter','$http', function($scope,$filter,$http) {
//     $scope.sample  = "Marek";
// }]);

app.controller('registerCtrl',['$scope','$http',function ($scope,$http) {
    $scope.trial = "trial123";

    $http({
        method: 'GET',
        url: 'api/registration'
    }).then(function successCallback(response) {
        $scope.users = response.data;
    }, function errorCallback(response) {
        console.log("error");
    });

}]);