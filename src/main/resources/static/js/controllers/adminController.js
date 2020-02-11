// var homeCtrl = angular.module('homeCtrl', ['ngRoute'] );
//
// homeCtrl.controller('home',['$scope', '$filter','$http', function($scope,$filter,$http) {
//     $scope.sample  = "Marek";
// }]);

app.controller('registerCtrl',['$scope','$http',function ($scope,$http) {

    //get all users
    $http({
        method: 'GET',
        url: 'api/registration'
    }).then(function successCallback(response) {
        $scope.users = response.data;
    }, function errorCallback(response) {
        console.log("error");
    });

    //delete user
    $scope.deleteUser = function(userId, index) {
        $scope.users.splice(index, 1);
        $http({
            method: 'DELETE',
            url: 'api/registration',
            params: {
               id : userId
            }
        }).then(function successCallback(response) {
            console.log("user has been deleted");
        }, function errorCallback(response) {
            console.log("error trying to delete user");
        });
    };
}]);

app.controller('createUserCtrl',['$scope','$http',function ($scope,$http) {
    $scope.user = {};
    var passconf = "";

    $scope.addUser = function (user) {

        // user.role = [];

        console.log(user);
        $http({
            method: 'POST',
            url: 'api/registration',
            data: user
        }).then(function successCallback(response) {
            alert("User has been added");
        }, function errorCallback(err) {
            console.log(err);
        });
    }
}]);