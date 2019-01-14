package com.syjun.demo.controller;

import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.model.MeetingRoom;
import com.syjun.demo.model.request.MeetingRoomParam;
import com.syjun.demo.service.DailyTimetableService;
import com.syjun.demo.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/meetingRoom")
@RestController
public class MeetingRoomAPIController {
    private final MeetingRoomService meetingRoomService;
    private final DailyTimetableService dailyTimetableService;

    @Autowired
    public MeetingRoomAPIController(MeetingRoomService meetingRoomService, DailyTimetableService dailyTimetableService){
        this.meetingRoomService = meetingRoomService;
        this.dailyTimetableService = dailyTimetableService;
    }

    @PostMapping({"", "/"})
    public MeetingRoom createMeetingRoom(
            @RequestBody MeetingRoomParam param
    ){
        return meetingRoomService.setMeetingRoom(param.getRoomName());
    }

    @GetMapping("/list")
    public List<MeetingRoom> getMeetingRoomList(){
        return meetingRoomService.getMeetingRoomList();
    }

    @GetMapping("/{selectedDate}/{roomId}")
    public DailyTimetable getDailyTimetable(
            @PathVariable long roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") String selectedDate
    ){
        return dailyTimetableService.getDailyTimetable(roomId,selectedDate);
    }
}
