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
<body>
<div>
    <h2 class="mb-4">${selectedDate}</h2>
    <div>
        <table>
            <tr>
                <th>날짜 선택</th>
                <td>
                    <select id="dateSelectBox" onchange="window.location.href=this.value">
                        <#list availableDateList as availableDate>
                            <option value="/view/${availableDate.value}">${availableDate.view}</option>
                        </#list>
                    </select>
                </td>

            </tr>
        </table>
        <input id="selectedDate" value="${selectedDate}" />
        <input id="roomId" value="" />
    </div>
</div>

<div style="width: 100%; overflow:auto">
    <table class="table" width="100%" border="0" cellspacing="0" cellpadding="0">
        <thead>
            <th width="3%">Time</th>
            <th>00:00<br>~00:30</th>
            <th>00:30<br>~01:00</th>
            <th>01:00<br>~01:30</th>
            <th>01:30<br>~02:00</th>
            <th>02:00<br>~02:30</th>
            <th>02:30<br>~03:00</th>
            <th>03:00<br>~03:30</th>
            <th>03:30<br>~04:00</th>
            <th>04:00<br>~04:30</th>
            <th>04:30<br>~05:00</th>
            <th>05:00<br>~05:30</th>
            <th>05:30<br>~06:00</th>
            <th>06:00<br>~06:30</th>
            <th>06:30<br>~07:00</th>
            <th>07:00<br>~07:30</th>
            <th>07:30<br>~08:00</th>
            <th>08:00<br>~08:30</th>
            <th>08:30<br>~09:00</th>
            <th>09:00<br>~09:30</th>
            <th>09:30<br>~10:00</th>
            <th>10:00<br>~10:30</th>
            <th>10:30<br>~11:00</th>
            <th>11:00<br>~11:30</th>
            <th>11:30<br>~12:00</th>
            <th>12:00<br>~12:30</th>
            <th>12:30<br>~13:00</th>
            <th>13:00<br>~13:30</th>
            <th>13:30<br>~14:00</th>
            <th>14:00<br>~14:30</th>
            <th>14:30<br>~15:00</th>
            <th>15:00<br>~15:30</th>
            <th>15:30<br>~16:00</th>
            <th>16:00<br>~16:30</th>
            <th>16:30<br>~17:00</th>
            <th>17:00<br>~17:30</th>
            <th>17:30<br>~18:00</th>
            <th>18:00<br>~18:30</th>
            <th>18:30<br>~19:00</th>
            <th>19:00<br>~19:30</th>
            <th>19:30<br>~20:00</th>
            <th>20:00<br>~20:30</th>
            <th>20:30<br>~21:00</th>
            <th>21:00<br>~21:30</th>
            <th>21:30<br>~22:00</th>
            <th>22:00<br>~22:30</th>
            <th>22:30<br>~23:00</th>
            <th>23:00<br>~23:30</th>
            <th>23:30<br>~24:00</th>
        </thead>
        <tbody>
            <#list meetingRoomResponse as meetingRoom>
                <tr id="${meetingRoom.roomName}">
                    <th>${meetingRoom.roomName}</th>
                    <#list meetingRoom.timetableArray as reserved>
                        <#if reserved>
                            <td class="table-danger"></td>
                        <#else>
                            <td class="table-success"><input type="checkbox" name="${meetingRoom.roomName}" value="${reserved?index}" /></td>
                        </#if>
                    </#list>
                </tr>
            </#list>
        </tbody>
    </table>
</div>
<script type="text/javascript">
    <#list meetingRoomResponse as meetingRoom>
    $('input[name="${meetingRoom.roomName}"]').change(function(){
        if($('input[name="${meetingRoom.roomName}"]:checked').length!=0){
            $('input[name!="${meetingRoom.roomName}"]').prop('disabled',true);
            $('#roomId').val(${meetingRoom.roomId});
        }
    });
    </#list>
    $('input[type="checkbox"]').change(function(){
        if($('input[type="checkbox"]:checked').length==0){
            $('input[type="checkbox"]').prop('disabled',false);
            $('#roomId').val("");
        }
    });

</script>
</body>


</html>