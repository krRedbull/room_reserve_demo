<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/fontawesome-all.min.css">
    <link rel="stylesheet" href="/css/datatables.min.css">
    <link rel="stylesheet" href="/css/fullcalendar.min.css">
    <link rel="stylesheet" href="/css/bootadmin.min.css">
    <script type="text/javascript" src="/jquery-3.3.1.min.js"></script>
</head>
<body class="bg-light">
<div class="content p-4">
    <h1><a href="/view/">회의실 예약 서비스</a></h1>
</div>
<div class="content p-4">
    <input class="form-control" style="width: 20%;" id="roomName" type="text" placeholder="회의실 이름을 입력하세요" />
    <button type="button" class="btn btn-primary" onclick="createdMeetingRoom();">등록</button>
</div>
<div class="content p-4">
    <table>
        <tr>
            <th>날짜 선택</th>
            <td>
                <select id="dateSelectBox" onchange="window.location.href=this.value">
                    <option value="" selected>날짜를 선택해주세요</option>
                    <#list availableDateList as availableDate>
                        <option value="/view/${availableDate.value}">${availableDate.view}</option>
                    </#list>
                </select>
            </td>
        </tr>
    </table>
</div>

<div class="content p-4" width="50%">
    <table class="table">
        <thead>
            <th>번호</th>
            <th>회의실 이름</th>
            <th>생성일</th>
        </thead>
        <tbody>
            <#if meetingRoomList?has_content>
                <#list meetingRoomList as meetingRoom>
                    <tr>
                        <th>${meetingRoom.id}</th>
                        <td>${meetingRoom.roomName}</td>
                        <td>${meetingRoom.createdAt}</td>
                    </tr>
                </#list>
            </#if>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    var createdMeetingRoom = function(){
        if($('#roomName').val().length==0){
            alert("회의실 이름을 입력하세요.");
            return 0;
        }

        var url = "/api/meetingRoom"

        var params = {
            'roomName': $('#roomName').val()
        }

        $.ajax({
            type: "POST",
            url: url,
            data: JSON.stringify(params),
            dataType: "json",
            contentType: "application/json; charset=utf-8",
            success: function(data){
                location.reload(true);
            },
            error: function(data){
                alert(data.responseJSON.message);
            }
        })

    }
</script>
</body>
</html>