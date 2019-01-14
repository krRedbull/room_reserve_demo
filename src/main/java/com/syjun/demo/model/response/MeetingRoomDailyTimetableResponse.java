package com.syjun.demo.model.response;

import javafx.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MeetingRoomDailyTimetableResponse {
    private long roomId;
    private String roomName;
    private String[] reserveNameArray;
    private boolean[] timetableArray;
}
