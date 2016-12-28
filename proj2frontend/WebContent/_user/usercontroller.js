app.controller('UserController',function($scope,$rootScope,$cookieStore,$location,UserService){
	$scope.user={id:'',username:'',password:'',email:'',role:'',isOnline:'',enabled:''};
	$scope.message;
	
	$scope.submit=function(){
		console.log('Entering - submit function in usercontroller')
		UserService.authenticate($scope.user)
		.then(function(response){
				$scope.user=response.data;
				$rootScope.currentUser=$scope.user;
				//key and value pair 
				$cookieStore.put('currentUser',$rootScope.currentUser)
				console.log('currentUser in rootScope ' + $rootScope.currentUser.id)
				$location.path("/home");
		},
		function(response){//invalid user name and password - failure 
			 console.log('invalid username and password')
			  $scope.message="Invalid Username and Password";
			  $location.path("/login");
		})
	}
	
	$scope.registerUser=function(){
		console.log('entering registerUser')
		UserService.registerUser($scope.user)
		.then(function(response){ //success 
			//response.data => user object
			console.log("registration success" + response.status)
			$scope.message="Registration successfull.. login using username and password.."
			$location.path("/login");
		},function(response){//failure
			console.log("registration failed" + response.status)
			//response.data => error object
			console.log(response.data)
			$scope.errorMessage="Registration failed...." + response.data.message
			$location.path("/register")
		})
	}
	
	$rootScope.logout=function(){
		console.log('logout function')
		delete $rootScope.currentUser;
		
		UserService.logout()
		.then(function(response){
			console.log("logged out successfully..");
			$scope.message="Logged out Successfully";
			$location.path('/login')
		},
		function(response){
			console.log(response.status);
		})
		
	}
	
	$rootScope.hasRole=function(role){
		if($rootScope.currentUser.role==undefined)
			return false;
		return $rootScope.currentUser.role==role;
	}
	
	
})