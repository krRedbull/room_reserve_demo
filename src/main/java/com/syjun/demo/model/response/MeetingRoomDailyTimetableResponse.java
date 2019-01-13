package com.syjun.demo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeetingRoomDailyTimetableResponse {
    private long roomId;
    private String roomName;
    private Boolean[] timetableArray;
}
