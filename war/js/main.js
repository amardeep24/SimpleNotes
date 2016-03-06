var main=angular.module('noteApp',['ngRoute']);

main.config(['$routeProvider', function($routeProvider) {
	   $routeProvider.
	   
	   when('/compose', {
	      templateUrl: 'compose.html', controller: 'composeController'
	   }).
	   
	   when('/view', {
	      templateUrl: 'view.html', controller: 'viewController'
	   }).
	   
	   when('/about', {
		      templateUrl: 'about.html', controller: 'aboutController'
		   });
		
	}]);



main.controller('composeController',function($scope,$http,$document,noteSavingService)
		{
			$scope.image={};
			$scope.image.show=false;
			$scope.save=function()
				{
					$scope.note={};
					//added on 28/02/2016
					$scope.note.noteId=generateNoteId();
					$scope.note.noteTitle=$scope.noteTitle;
					$scope.note.noteContent=$scope.noteContent;
					$scope.note.noteDate=getDate();
					$scope.note.flag=true;
					$scope.note.noteImage=$scope.image.result;
					noteSavingService.saveNote($scope.note)
					.success(function (result)
					        {
			        	$scope.result = result;
			        	$scope.msg="compose_note_status_success";
			        	$scope.noteTitle=null;
			        	$scope.noteContent=null;
			        	document.getElementById('notePicker').value = null;
			        	$scope.image.show=false;
			        });
				}
			$scope.clear=function()
				{
					$scope.noteTitle=null;
					$scope.noteContent=null;
					document.getElementById('notePicker').value = null;
					$scope.result=null;
					$scope.image.show=false;
					$scope.note={};
				}
			$scope.uploadFile=function(files)
			{
						var reader=new FileReader();
						reader.onload=function(file)
								{
									$scope.$apply(function(){
									//$scope.image.result=reader.result;
									var image=new Image();
									image.src=reader.result;
									$scope.image.result=compress(image,10,'jpg');
									//console.log(reader.result);
									$scope.image.show=true;
								});
								}
						reader.readAsDataURL(files[0]);
			}
			
		});
main.controller('viewController',function($scope,$http,$window,deleteNoteService,editNoteService)
		{
			 $scope.notes=[];
			 $scope.result={};
			 $http({
		            method: 'GET',
		            url: '/pages/getNotes.do',
		        })
		        .success(function (result)
		        {
		        	for(var i=0;i<result.length;i++)
		        		{
		        			if(result[i].noteImage === null)
		        				{
		        					result[i].imageShow=false;
		        				}
		        			else
		        				result[i].imageShow=true;
		        		}
		        	$scope.notes = result;
		        });
			 $scope.delete=function(note)
			 {
				 var noteIndex=$scope.notes.indexOf(note);
				 delete note.imageShow;
				 deleteNoteService.deleteNote(note).
				 success(function(result)
						 {
					 		//$window.alert(result.noteTitle+" "+result.status);
					 		$scope.result=result;
					 		if(noteIndex > -1)
					 			{
					 				$scope.notes.splice(noteIndex, 1);
					 			}
					 		//$window.location.reload();
						 });
				 
				
			 }
			 $scope.edit=function(note)
			 {
				 note.flag=false;
			 }
			 $scope.save=function(note)
			 {
				 if(note.noteTitle===null&&note.noteContent===null)
					 {
					 	alert("Fill mandatory details");
					 	console.log("in if block");
					 }
				 else
				 {
				 console.log("in else block of save in edit func");
				 note.flag=true;
				 note.noteDate=getDate();
				 delete note.imageShow;
				 editNoteService.editNote(note)
				 .success(function (result)
					{
			        	note.flag=true;
			        });
				 }
			 }
			 $scope.cancel=function(note)
			 {
				 note.flag=true;
			 }
			 $scope.clear=function(note)
			 {
				 note.noteTitle=null;
				 note.noteContent=null;
			 }
			 /*$scope.editedNote={};
			 $scope.edit=function(oldNote)
				 {
				 	 console.log($scope.editedNote.noteTitle);
					 $scope.editedNote.noteId=oldNote.noteId;
					 $scope.editedNote.noteDate=getDate();
					 console.log(oldNote.noteId);
					 $scope.editing=false;
					 editNoteService.editNote($scope.editedNote)
					 .success(function(){
						
						 $window.location.reload();
					 });
					
				 }
			 $scope.clear=function()
			 {
				 $scope.noteTitle=null;
				 $scope.noteContent=null;
			 }
			 $scope.cancel=function()
			 {
				 $scope.editing=false;
			 }*/
			 
			
			
	
		});

main.controller('aboutController',function($scope)
		{
			
	
		});
main.service('noteSavingService',function($http)
		{
			this.saveNote=function(note)
			{
				return $http({
					method:'POST',
					url:'/pages/saveNote.do',
					data:note
				});
				
			}
		});
main.service('deleteNoteService',function($http)
		{
			this.deleteNote=function(note)
			{
				return $http({
					method:'POST',
					url:'/pages/deleteNote.do',
					data:note
				})
			}
		});
main.service('editNoteService',function($http)
		{
			this.editNote=function(note)
			{
				return $http({
					method:'POST',
					url:'/pages/editNote.do',
					data:note
				})
			}
});
/*function getDate()
{
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!

    var yyyy = today.getFullYear();
    if(dd<10){
        dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    var today = dd+'/'+mm+'/'+yyyy;
    return today;

}*/
function getDate()
{
	var date=new Date();
	var monthNames = ["Jan", "Feb", "Mar", "Apr", "May", "June",
	  "July", "Aug", "Sept", "Oct", "Nov", "Dec"];
	var dayNames=["Sun","Mon","Tue","Wed","Thu","Fri","Sat"];
	var year=date.getFullYear();
	var month=monthNames[date.getMonth()];
	var day=dayNames[date.getDay()];
	var dayOfMonth=date.getDate();
	var hours = date.getHours();
	var minutes = date.getMinutes();
	var ampm = hours >= 12 ? 'PM' : 'AM';
	hours = hours % 12;
	hours = hours ? hours : 12; // the hour '0' should be '12'
	minutes = minutes < 10 ? '0'+minutes : minutes;
	var time = hours + ':' + minutes + ' ' + ampm;
	var today=day+', '+dayOfMonth+" "+month+" "+year+" "+time;
	console.log(today);
	return today;
}
function encodeURL(str){
    return str.replace(/\+/g, '-').replace(/\//g, '_').replace(/\=+$/, '');
}
function decodeUrl(str){
    str = (str + '===').slice(0, str.length + (str.length % 4));
    return str.replace(/-/g, '+').replace(/_/g, '/');
}
function formatImageUrl(str)
{
    
    var data=new String("data:").concat( str.substring(4));
    var base=new String("data:").concat( str.substring(4)).indexOf("base64");
    var pre=data.substring(0,base).concat(";").concat(data.substring(base));
    return (pre.substring(0,pre.indexOf("4")+1).concat(new String(",").concat(pre.substring(pre.indexOf("4")+1)))).concat("=");
}
function generateNoteId()
{
	return Math.floor(100000 + Math.random() * 900000);
}
function compress(source_img_obj, quality, output_format){
    
	
    var mime_type = "image/jpeg";
    if(typeof output_format !== "undefined" && output_format=="png"){
       mime_type = "image/png";
    }
    

    var cvs = document.createElement('canvas');
    cvs.width = source_img_obj.naturalWidth;
    cvs.height = source_img_obj.naturalHeight;
    var ctx = cvs.getContext("2d").drawImage(source_img_obj, 0, 0);
    var newImageData = cvs.toDataURL(mime_type, quality/100);
	// console.log(newImageData);
    return newImageData;
}
