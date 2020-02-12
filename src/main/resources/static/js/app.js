
var app = angular.module('myApp', ["ngRoute"]);

// app.controller('myCtrl', function($scope) {
//     $scope.firstName = "John";
//     $scope.lastName = "Doe";
// });

app.config(function($routeProvider) {
    $routeProvider
        .when("/register", {
            controller : "registerCtrl",
            templateUrl : "partials/user.html",
            css : "css/adminPanel.css"
        })
        .when("/user-create",{
            controller: "createUserCtrl",
            templateUrl : "partials/user-create.html",
            css : "css/adminPanel.css"
        });
});
