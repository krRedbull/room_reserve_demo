package com.syjun.demo.service;

import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.model.Reservation;
import com.syjun.demo.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author : red_bull
 * @date : 2019. 1. 12.   PM 8:29
 */
@Service
public class MeetingRoomReservationService {

    private final DailyTimetableService dailyTimetableService;
    private final MeetingRoomRepository meetingRoomRepository;
    private final ReservationService reservationService;

    @Autowired
    public MeetingRoomReservationService(DailyTimetableService dailyTimetableService, MeetingRoomRepository meetingRoomRepository, ReservationService reservationService){
        this.dailyTimetableService = dailyTimetableService;
        this.meetingRoomRepository = meetingRoomRepository;
        this.reservationService = reservationService;
    }

    @Transactional
    public void reserveMeetingRoom(String selectedDate, long roomId, String reserveName, long reserveTime, int repeatTime) throws ParseException {
        if(repeatTime > 0){
            this.reserveRepeat(selectedDate, roomId, reserveName, reserveTime, repeatTime);
        }else{
            repeatTime = 0;
            this.reserve(selectedDate, roomId, reserveName, reserveTime);
        }
    }

    private void reserveRepeat(String selectedDate, long roomId, String reserveName, long reserveTime, int repeatTime) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        Date date = sdf.parse(selectedDate);

        for(int i=0; i<repeatTime; i++){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, (7 * i));

            sdf.format(c.getTime());
            this.reserve(sdf.format(c.getTime()), roomId, reserveName, reserveTime);
        }


    }

    private void reserve(String selectedDate, long roomId, String reserveName, long reserveTime){
        DailyTimetable dailyTimetable = dailyTimetableService.getDailyTimetable(roomId, selectedDate);
        if(isAvailable(reserveTime, dailyTimetable.getTimetable())){
            dailyTimetable.setTimetable(dailyTimetable.getTimetable() + reserveTime);
            dailyTimetableService.updateDailyTimetable(dailyTimetable);

            Reservation reservation = new Reservation();
            reservation.setRoomId(roomId);
            reservation.setReserveName(reserveName);
            reservation.setReserveTimetable(reserveTime);

            reservationService.saveReservation(reservation);
        }
    }


    private boolean isAvailable(long requestTimetable, long existTimetable){

        long a = requestTimetable & existTimetable;

        if(a==0){
            return true;
        }else {
            return false;
        }
    }

}
