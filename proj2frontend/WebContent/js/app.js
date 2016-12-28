var app=angular.module("myApp",['ngRoute','ngCookies'])
app.config(function($routeProvider){
	console.log('entering configuration')
	$routeProvider
	.when('/login',{
		controller:'UserController',
		templateUrl:'_user/login.html'
	})
	.when('/home',{
		templateUrl:'_home/home.html'
	})
	.when('/register',{
		controller:'UserController',
		templateUrl:'_user/register.html'
	})
	.when('/postJob',{
		controller:'JobController',
		templateUrl:'_job/createJob.html'
	})
	.when('/getAllJobs',{
		controller:'JobController',  // write a function to get all jobs from the backend => JobService
		templateUrl:'_job/jobTitles.html'  // to display the job titles in html page
	})
	.when('/uploadPicture',{
		templateUrl:'_user/uploadPicture.html'
})
})
app.run(function($cookieStore,$rootScope,$location,UserService){  //entry point
	
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
		
	$rootScope.logout=function(){
		console.log('logout function')
		delete $rootScope.currentUser;
		$cookieStore.remove('currentUser')
		UserService.logout()
		.then(function(response){
			console.log("logged out successfully..");
			$rootScope.message="Logged out Successfully";
			$location.path('/login')
		},
		function(response){
			console.log(response.status);
		})
		
	}	
})