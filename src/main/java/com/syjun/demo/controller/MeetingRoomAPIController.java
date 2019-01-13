package com.syjun.demo.controller;

import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.model.MeetingRoom;
import com.syjun.demo.service.DailyTimetableService;
import com.syjun.demo.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
