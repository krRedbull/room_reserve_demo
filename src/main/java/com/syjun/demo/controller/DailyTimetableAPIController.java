package com.syjun.demo.controller;

import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.service.DailyTimetableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/dailyTimetable")
@RestController
public class DailyTimetableAPIController {
    private final DailyTimetableService dailyTimetableService;

    @Autowired
    public DailyTimetableAPIController(DailyTimetableService dailyTimetableService){
        this.dailyTimetableService = dailyTimetableService;
    }

    @GetMapping("/{selectedDate}/{roomId}")
    public DailyTimetable getDailyTimetable(
            @PathVariable String selectedDate,
            @PathVariable long roomId
    ){
        return dailyTimetableService.getDailyTimetable(roomId,selectedDate);
    }
}
