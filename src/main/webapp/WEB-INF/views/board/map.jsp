<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=9qbj4wcua8&submodules=geocoder"></script>
	<script  src="http://code.jquery.com/jquery-latest.min.js"></script>
	<link rel="shortcut icon" href="/resources/img/favicon.ico" type="image/x-icon">
	
	<script type="text/javascript" src="http://code.jquery.com/jquery-2.2.4.min.js"></script>
</head>
<body>
<div class="search">
	<input id="address" type="text" placeholder="검색할 주소">
	<input id="submit" type="button" value="주소검색">
</div>
<div id="map" style="width:1000px;height:500px;"></div>

<div id="mapList"></div>

<script>

//mapList 호출
selectMapList();

var mapOptions = {
    center: new naver.maps.LatLng(37.3595704, 127.105399),
    zoom: 15,
    mapTypeControl: true
};

var map = new naver.maps.Map('map', mapOptions);

var infoWindow = new naver.maps.InfoWindow({
    anchorSkew: true
});

//위치검색한 좌표 지도에 찍어주기
function searchAddressToCoordinate(address) {
    naver.maps.Service.geocode({
        query: address
    }, function(status, response) {
        if (status === naver.maps.Service.Status.ERROR) {
            return alert('Something Wrong!');
        }

        if (response.v2.meta.totalCount === 0) {
            return alert('올바른 주소를 입력해주세요.');
        }

        var htmlAddresses = [],
            item = response.v2.addresses[0],
            point = new naver.maps.Point(item.x, item.y);

        if (item.roadAddress) {
            htmlAddresses.push('[도로명 주소] ' + item.roadAddress);
        }

        if (item.jibunAddress) {
            htmlAddresses.push('[지번 주소] ' + item.jibunAddress);
        }

        if (item.englishAddress) {
            htmlAddresses.push('[영문명 주소] ' + item.englishAddress);
        }

        infoWindow.setContent([
            '<div style="padding:10px;min-width:200px;line-height:150%;">',
            '<h4 style="margin-top:5px;">검색 주소 : '+ address +'</h4><br />',
            htmlAddresses.join('<br />'),
            '</div>'
        ].join('\n'));

        map.setCenter(point);
        infoWindow.open(map, point);
        
        insertAddress(item.roadAddress, item.x, item.y);
        
    });
}

//위치 검색
function initGeocoder() {
    if (!map.isStyleMapReady) {
        return;
    }

    $('#address').on('keydown', function(e) {
        var keyCode = e.which;

        if (keyCode === 13) { // Enter Key
            searchAddressToCoordinate($('#address').val());
        }
    });

    $('#submit').on('click', function(e) {
        e.preventDefault();

        searchAddressToCoordinate($('#address').val());
    });

}

naver.maps.Event.once(map, 'init_stylemap', initGeocoder);

//검색정보 BoardMap 테이블에 저장
function insertAddress(address, latitude, longitude) {
	
	$.ajax({
		type : 'post'
		, url : '/board/map'
		, data : {'address' : address
				, 'latitude' : latitude
				, 'longitude' : longitude}
		, success : function() {
			
			$("#mapList").html("");
			selectMapList();
		}
	})
}

//BoardMap 테이블 위치저장 정보 가져오기
function selectMapList() {
	
	$.ajax({
		type : 'get'
		, url : '/board/mapList'
		, dataType : 'json'
		, success : function(data) {
			
			var mapList = "";
			
			mapList += "<table>"
			mapList += "<tr>"
			mapList += "	<th>seq</th>"
			mapList += "	<th>대분류</th>"
			mapList += "	<th>소분류</th>"
			mapList += "	<th>위도</th>"
			mapList += "	<th>경도</th>"
			mapList += "</tr>"
			
			for(var i=0; i<data.length; i++) {
				mapList += "<tr onclick='moveMap(\"" + data[i].len + "\" , \"" + data[i].lat +"\")'>"
				mapList += "	<td>" + data[i].mapSeq + "</td>"
				mapList += "	<td>" + data[i].mainAddress + "</td>"
				mapList += "	<td>" + data[i].middleAddress + "</td>"
				mapList += "	<td>" + data[i].lat + "</td>"
				mapList += "	<td>" + data[i].len + "</td>"
				mapList += "</tr>"
			}
			
			mapList += "</table>"
			
			$("#mapList").append(mapList);
		}
		
	})
}

function moveMap(len, lat) {
	var mapOptions = {
		    center: new naver.maps.LatLng(len, lat),
		    zoom: 15,
		    mapTypeControl: true
		};

		var map = new naver.maps.Map('map', mapOptions);

		var marker = new naver.maps.Marker({
		    position: new naver.maps.LatLng(len, lat),
		    map: map
		});

}
</script>
</body>
</html>