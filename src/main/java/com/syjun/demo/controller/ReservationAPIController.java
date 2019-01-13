package com.syjun.demo.controller;

import com.syjun.demo.service.MeetingRoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;

/**
 * @author : red_bull
 * @date : 2019. 1. 12.   PM 8:26
 */
@RequestMapping("/api/reservation")
@RestController
public class ReservationAPIController {

    private final MeetingRoomReservationService meetingRoomReservationService;

    @Autowired
    public ReservationAPIController(MeetingRoomReservationService meetingRoomReservationService){
        this.meetingRoomReservationService = meetingRoomReservationService;

    }

    @PostMapping("/{selectedDate}/{roomId}")
    public Object reserveMeetingRoom(
            @PathVariable long roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") String selectedDate,
            @RequestParam String reserveName,
            @RequestParam long reserveTime,
            @RequestParam(required = false) int repeatTime
    ) throws ParseException {
        meetingRoomReservationService.reserveMeetingRoom(selectedDate, roomId, reserveName, reserveTime, repeatTime);

        return null;
    }
}
