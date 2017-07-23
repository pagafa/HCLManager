var urls = null;
var locations = null;

function showUrl(id) {
	if (id > 0) {
		$('#urlId').val(id);
		$('#urlName').val(urls[id].name);
		$('#urlUrl').val(urls[id].url);
		$('#urlRemoveButton').show();
	} else {
		$('#urlId').val(-1);
		$('#urlName').val('');
		$('#urlUrl').val('http://');
		$('#urlRemoveButton').hide();
	}
	$('#urlFloat').show();
}

function editUrl() {
	showUrl($(this).parent().attr('urlId'));
}

function saveUrl() {
	var url = {
		id : $('#urlId').val(),
		name : $('#urlName').val(),
		url : $('#urlUrl').val()
	};

	$.ajax({
		url : 'url/update',
		type : 'POST',
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(url)
	}).done(function() {
		hideFloats();
		listUrls();
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Error: " + textStatus);
	});
}

function removeUrl() {
	$.ajax({
		url : 'url/remove',
		data : {
			id : $('#urlId').val()
		}
	}).done(function() {
		hideFloats();
		listUrls();
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Error: " + textStatus);
	});
}

function listUrls() {
	$('.tab').removeClass('selected');
	$('#tabUrls').addClass('selected');

	$.getJSON("url/list", function(data) {
		urls = {};
		var dest = $('.list');
		dest.empty();
		for (var i = 0; i < data.length; i++) {
			urls[data[i].id] = data[i];
			var ele = $('<div/>').addClass('element').attr('urlId', data[i].id);
			ele.append($('<div/>').addClass('edit').on('click', editUrl));
			ele.append($('<div/>').addClass('name').html(data[i].name));
			ele.append($('<div/>').addClass('url').html(data[i].url));
			dest.append(ele);
		}
	});
}

function showLocation(id) {
	if (id > 0) {
		$('#locationId').val(id);
		$('#locationName').val(locations[id].name);
		$('#locationLat').val(locations[id].latitude);
		$('#locationLon').val(locations[id].longitude);
		$('#locationRemoveButton').show();
	} else {
		$('#locationId').val(-1);
		$('#locationName').val('');
		$('#locationLat').val(0);
		$('#locationLon').val(0);
		$('#locationRemoveButton').hide();
	}
	$('#locationFloat').show();
}

function editLocation() {
	showLocation($(this).parent().attr('locationId'));
}

function saveLocation() {
	var location = {
		id : $('#locationId').val(),
		name : $('#locationName').val(),
		latitude : $('#locationLat').val(),
		longitude : $('#locationLon').val()
	};

	$.ajax({
		url : 'location/update',
		type : 'POST',
		contentType : "application/json; charset=utf-8",
		data : JSON.stringify(location)
	}).done(function() {
		hideFloats();
		listLocations();
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Error: " + textStatus);
	});
}

function removeLocation() {
	$.ajax({
		url : 'location/remove',
		data : {
			id : $('#locationId').val()
		}
	}).done(function() {
		hideFloats();
		listLocations();
	}).fail(function(jqXHR, textStatus, errorThrown) {
		alert("Error: " + textStatus);
	});
}
function listLocations() {
	$('.tab').removeClass('selected');
	$('#tabLocations').addClass('selected');

	$.getJSON("location/list", function(data) {
		locations = {};
		var dest = $('.list');
		dest.empty();
		for (var i = 0; i < data.length; i++) {
			locations[data[i].id] = data[i];
			var ele = $('<div/>').addClass('element').attr('locationId', data[i].id);
			ele.append($('<div/>').addClass('edit').on('click', editLocation));
			ele.append($('<div/>').addClass('name').html(data[i].name));
			ele.append($('<div/>').addClass('lat').html(data[i].latitude));
			ele.append($('<div/>').addClass('lon').html(data[i].longitude));
			dest.append(ele);
		}
	});
}

function hideFloats() {
	$('.float').hide();
}

$(function() {
	$('#tabUrls').on('click', listUrls);
	$('#tabLocations').on('click', listLocations);

	$('#urlSaveButton').on('click', saveUrl);
	$('#urlRemoveButton').on('click', removeUrl);

	$('#locationSaveButton').on('click', saveLocation);
	$('#locationRemoveButton').on('click', removeLocation);

	$('.cancelButton').on('click', function() {
		hideFloats();
	});

	$('#addButton').on('click', function() {
		if ($('#tabUrls.selected').length > 0) {
			showUrl(-1);
		} else if ($('#tabLocations.selected').length > 0) {
			showLocation(-1);
		}
	});

	listUrls();
});