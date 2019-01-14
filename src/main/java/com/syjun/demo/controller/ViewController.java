package com.syjun.demo.controller;

import com.google.common.collect.Lists;
import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.model.MeetingRoom;
import com.syjun.demo.model.Reservation;
import com.syjun.demo.model.response.AvailableDateResponse;
import com.syjun.demo.model.response.MeetingRoomDailyTimetableResponse;
import com.syjun.demo.service.DailyTimetableService;
import com.syjun.demo.service.MeetingRoomService;
import com.syjun.demo.service.ReservationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RequestMapping("/view")
@RestController
@Slf4j
public class ViewController {

    @Value("${reserve.max-repeat}")
    private int MAX_REPEAT;

    @Value("${reserve.max-available-day-after}")
    private int MAX_AVAILABLE_DAY_AFTER;

    private final MeetingRoomService meetingRoomService;
    private final DailyTimetableService dailyTimetableService;
    private final ReservationService reservationService;

    @Autowired
    public ViewController(MeetingRoomService meetingRoomService, DailyTimetableService dailyTimetableService, ReservationService reservationService){
        this.meetingRoomService = meetingRoomService;
        this.dailyTimetableService = dailyTimetableService;
        this.reservationService = reservationService;
    }

    @GetMapping({"","/","/index"})
    public ModelAndView index(
            ModelAndView mv
    ){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        mv.addObject("availableDateList", this.getAvailableDate());
        mv.addObject("meetingRoomList", meetingRoomService.getMeetingRoomList());
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
            List<Reservation> reservationList = reservationService.getReservationOfMeetingRoom(meetingRoom.getId(), selectedDate);

            String[] reserveNameTable = new String[48];
            boolean[] booleans = new boolean[48];

            reservationList.stream().forEach(reservation -> {
                for(int i=0; i< Long.toBinaryString(reservation.getReserveTimetable()).length(); i++){
                    if(!booleans[i]) {
                        booleans[i] = (reservation.getReserveTimetable() & (1 << i)) != 0;
                        reserveNameTable[i] = (reservation.getReserveTimetable() & (1<< i)) !=0 ? reservation.getReserveName() : null;
                    }
                }
            });
            dailyTimetableResponses.add( new MeetingRoomDailyTimetableResponse(meetingRoom.getId(), meetingRoom.getRoomName(), reserveNameTable, booleans));
        });

        mv.addObject("maxRepeat", MAX_REPEAT);
        mv.addObject("availableDateList", this.getAvailableDate());
        mv.addObject("meetingRoomResponse", dailyTimetableResponses);
        mv.addObject("selectedDate", selectedDate);
        if(this.isOldDate(selectedDate)){
            mv.setViewName("reservationOld");
        }else{
            mv.setViewName("reservation");
        }
        return mv;
    }

    private List<AvailableDateResponse> getAvailableDate(){
        List<AvailableDateResponse> availableDateList = Lists.newArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy / MM / dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        Date currentDate = new Date();

        for(int i=0; i<MAX_AVAILABLE_DAY_AFTER; i++){
            Calendar c = Calendar.getInstance();
            c.setTime(currentDate);
            c.add(Calendar.DATE, i);
            availableDateList.add(new AvailableDateResponse(sdf.format(c.getTime()), sdf2.format(c.getTime())));
        }

        return availableDateList;
    }

    private boolean isOldDate(String selectedDate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date current = new Date();
        Date selected;
        try {
            selected = sdf.parse(selectedDate);
            Calendar c = Calendar.getInstance();
            c.setTime(selected);
            c.add(Calendar.DATE, 1);
            selected = c.getTime();
            if(selected.getTime() < current.getTime()){
                return true;
            }
        } catch (ParseException e) {
            return false;
        }

        return false;
    }

}
