package com.syjun.demo.controller;

import com.google.common.base.MoreObjects;
import com.syjun.demo.exceptions.InvalidReserveTimeException;
import com.syjun.demo.model.Reservation;
import com.syjun.demo.model.request.ReserveMeetingRoomParam;
import com.syjun.demo.service.MeetingRoomReservationService;
import com.syjun.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @author : red_bull
 * @date : 2019. 1. 12.   PM 8:26
 */
@RequestMapping("/api/reservation")
@RestController
public class ReservationAPIController {

    private final MeetingRoomReservationService meetingRoomReservationService;
    private final ReservationService reservationService;

    @Autowired
    public ReservationAPIController(MeetingRoomReservationService meetingRoomReservationService, ReservationService reservationService){
        this.meetingRoomReservationService = meetingRoomReservationService;
        this.reservationService = reservationService;

    }

    @PostMapping("/{selectedDate}/{roomId}")
    public Object reserveMeetingRoom(
            @PathVariable long roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") String selectedDate,
            @RequestBody ReserveMeetingRoomParam param
//            @RequestBody long reserveTime,
//            @RequestBody(required = false) int repeatTime
    ) throws ParseException, InvalidReserveTimeException {
        meetingRoomReservationService.reserveMeetingRoom(selectedDate, roomId, param.getReserveName(), param.getReserveTime(), MoreObjects.firstNonNull(param.getRepeatTime(),0));

        return null;
    }

    @GetMapping("/{selectedDate}/{roomId}")
    public List<Reservation> getReservationListOfMeetingRoom(
            @PathVariable long roomId,
            @PathVariable @DateTimeFormat(pattern = "yyyyMMdd") String selectedDate
    ){
         return reservationService.getReservationOfMeetingRoom(roomId, selectedDate);
    }
}
