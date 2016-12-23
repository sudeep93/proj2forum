app.factory('UserService',function($http){
	var userService=this;
	var BASE_URL="http://localhost:8090/backend"
	userService.authenticate=function(user){
		return $http.post(BASE_URL + "/login",user);
	};
	
	userService.registerUser=function(user){
		console.log('entering service for registration')
		return $http.post(BASE_URL + "/register",user) 
	};
	
	userService.logout=function(){
		console.log('entering logout service')
		return $http.put(BASE_URL + "/logout")
	};
	
	return userService;
})