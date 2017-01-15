app.controller("ChatCtrl", function($scope,$rootScope,$cookieStore ,ChatService) {
    $scope.messages = [];
    $scope.message = "";
    $scope.max = 140;
    
	if($rootScope.currentUser==undefined)
		$rootScope.currentUser=$cookieStore.get('currentUser')
    
    $scope.addMessage = function() {
      ChatService.send($rootScope.currentUser.username + " : " + $scope.message);
      $rootScope.currentUser=$cookieStore.get('currentUser');
      
      $scope.message = "";
    };
    
    ChatService.receive().then(null, null, function(message) {
    	$rootScope.currentUser=$cookieStore.get('currentUser')
      $scope.messages.push(message);
    });
  });