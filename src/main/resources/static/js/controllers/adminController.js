
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

    var passconf = "";

    $scope.role = {
        name: 'Customer'
    };
    // $scope.specialValue = {
    //     "id": "12345",
    //     "value": "green"
    // };

    // $scope.roleData = [
    //     {
    //         roleText: 'Choose role which created user will have: ',
    //         answer1: [
    //             {option1: 'Admin'},
    //             {option1: 'User'},
    //             {option1: 'Customer'},
    //         ]
    //     }
    // ];

    $scope.addUser = function (user,role) {

        console.log("Rola to " + role.name);
        console.log(user);
        $http({
            method: 'POST',
            url: 'api/registration',
            data: user,
            params:{
                roleName: role.name
            }
        }).then(function successCallback(response) {
            alert("User has been added");
        }, function errorCallback(err) {
            console.log(err);
        });
    }
}]);