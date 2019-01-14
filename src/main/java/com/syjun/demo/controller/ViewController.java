package com.syjun.demo.controller;

import com.google.common.collect.Lists;
import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.model.MeetingRoom;
import com.syjun.demo.model.response.AvailableDateResponse;
import com.syjun.demo.model.response.MeetingRoomDailyTimetableResponse;
import com.syjun.demo.service.DailyTimetableService;
import com.syjun.demo.service.MeetingRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/view")
@RestController
@Slf4j
public class ViewController {

    private final MeetingRoomService meetingRoomService;
    private final DailyTimetableService dailyTimetableService;

    @Autowired
    public ViewController(MeetingRoomService meetingRoomService, DailyTimetableService dailyTimetableService){
        this.meetingRoomService = meetingRoomService;
        this.dailyTimetableService = dailyTimetableService;
    }

    @GetMapping({"","/","/index"})
    public ModelAndView index(
            ModelAndView mv
    ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        mv.addObject("selectedDate", sdf.format(new Date()));
        mv.setViewName("index");
        return mv;
    }

    @GetMapping("/{selectedDate}")
    public ModelAndView reservationView(
            @PathVariable String selectedDate,
            ModelAndView mv
    ){
        List<MeetingRoom> meetingRoomList = meetingRoomService.getMeetingRoomList();
        List<MeetingRoomDailyTimetableResponse> dailyTimetableResponses = Lists.newArrayList();

        meetingRoomList.stream().forEach(meetingRoom -> {
            DailyTimetable dailyTimetable = dailyTimetableService.getDailyTimetable(meetingRoom.getId(), selectedDate);
            dailyTimetableResponses.add( new MeetingRoomDailyTimetableResponse(meetingRoom.getId(), meetingRoom.getRoomName(), dailyTimetable.getTimetableArray()));
        });

        mv.addObject("availableDateList", this.getAvailableDate());
        mv.addObject("meetingRoomResponse", dailyTimetableResponses);
        mv.addObject("selectedDate", selectedDate);
        mv.setViewName("reservation");
        return mv;
    }

    private List<AvailableDateResponse> getAvailableDate(){
        List<AvailableDateResponse> availableDateList = Lists.newArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();

        for(int i=0; i<60; i++){
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DATE, i);
            availableDateList.add(new AvailableDateResponse(sdf.format(c.getTime()), sdf2.format(c.getTime())));
        }

        return availableDateList;
    }

//    private boolean[] getTimetableArray(long timetable){
//        boolean[] booleans = new boolean[48];
//        for(int i=0; i<Long.toBinaryString(timetable).length(); i++){
//            booleans[i] = (timetable & (1<< i)) !=0;
//        }
//        return booleans;
//    }

}
