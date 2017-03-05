var app = angular.module('intershipApp', ['ngRoute', 'ui.bootstrap']);
app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "list.html"
        })
        .when("/add", {
            templateUrl: "add-edit.html"
        })
        .when("/edit", {
            templateUrl: "add-edit.html"
        });
});
app.controller('userController', function ($scope, $http, $location, $filter) {
    var isAddScreen;
    var goToUrl = function (url) {
        $location.path(url);
    };
    var refresh = function () {
        $http.get("/intership/user")
            .then(function (response) {
                $scope.users = response.data;
            });
    };

    $scope.currentPage = 1;
    $scope.itemsPerPage = 2;
    $scope.booleanToLabel = function (value) {
        return value ? 'YES' : 'NO';
    };
    $scope.goToList = function () {
        $scope.currentUser = {};
        goToUrl('/');
        refresh();
    };
    $scope.isAdminOptions = [
        {id: true, name: $scope.booleanToLabel(true)},
        {id: false, name: $scope.booleanToLabel(false)}
    ];
    $scope.goToAdd = function () {
        isAddScreen = true;
        $scope.currentUser = {};
        goToUrl('/add');
    };
    $scope.goToEdit = function (user) {
        isAddScreen = false;
        $scope.currentUser = user;
        $scope.currentUser.formattedCreatedDate = $filter('date')(user.createdDate, 'yyyy-MM-dd HH:mm:ss');
        $scope.currentUser.isAdminOption = user.isAdmin ? $scope.isAdminOptions[0] : $scope.isAdminOptions[1];
        goToUrl('/edit');
    };
    $scope.delete = function (userId) {
        $http.delete("/intership/user?userId=" + userId)
            .then(function () {
                $scope.goToList();
            });
    };
    $scope.addEdit = function () {
        $scope.currentUser.createdDate = new Date($scope.currentUser.formattedCreatedDate).getTime();
        $scope.currentUser.isAdmin = $scope.currentUser.isAdminOption.id;
        delete $scope.currentUser.formattedCreatedDate;
        delete $scope.currentUser.isAdminOption;

        $http({
            method: isAddScreen ? "POST" : "PUT",
            url: "/intership/user",
            data: $scope.currentUser,
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(function () {
            $scope.goToList();
        });
    };
    $scope.edit = function () {
        var config = {
            headers: {
                'Content-Type': 'application/json'
            }
        };
        $http.put("/intership/user", $scope.currentUser, config)
            .then(function () {
                $scope.goToList();
            });
    };
    $scope.showRequiredError = function (form, field) {
        return (form.$submitted || field.$dirty) && field.$error.required;
    };

    $scope.goToList();
});
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);