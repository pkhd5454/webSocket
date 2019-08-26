var ajaxManager = (function(){
	var getFriendRequest = function(myId, callback) {
		$.getJSON("/socialChat/friendRequest/" + myId, callback);
	};
	
	var acceptFriendRequest = function(obj, callback) {
		$.ajax({
			type:'post',
			url:'/socialChat/friendRequest/'+obj.user+"/"+obj.friend,
			dataType:'json',
			contentType:'application/json',
			beforeSend: function(xhr){
				xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
			},
			success: callback
		});
	};
	
	var denyFriendRequest = function(obj, callback) {
		$.ajax({
			type:'delete',
			url:'/socialChat/friendRequest/'+obj.user+"/"+obj.friend,
			dataType:'json',
			contentType:'application/json',
			beforeSend: function(xhr){
				xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
			},
			success: callback
		});
	};
	
	var getFriend = function(myId, callback) {
		$.getJSON("/socialChat/userName/" + myId, callback);
	};
	
	var get = function(myId, userName, callback) {
		$.getJSON("/socialChat/userName/"+myId+"/"+userName, callback);
	};
	
	var add = function(obj, callback, error) {
		$.ajax({
			type:'post',
			url:'/socialChat/friend/'+obj.user+"/"+obj.friend,
			beforeSend: function(xhr){
				xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
			},
			success: callback,
			error: error
		});
	};
	
	var del = function(obj, callback) {
		$.ajax({
			type:'delete',
			url:'/socialChat/friend/'+obj.user+"/"+obj.friend,
			dataType:'json',
			contentType:'application/json',
			beforeSend: function(xhr){
				xhr.setRequestHeader(obj.csrf.headerName, obj.csrf.token);
			},
			success: callback
		});
	};
	
	return {
		getFriendRequest:getFriendRequest,
		acceptFriendRequest:acceptFriendRequest,
		denyFriendRequest:denyFriendRequest,
		getFriend:getFriend,
		get:get,
		add:add,
		del:del
	}
	
})();