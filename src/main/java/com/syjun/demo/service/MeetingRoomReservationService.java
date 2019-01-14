package com.syjun.demo.service;

import com.google.common.collect.Lists;
import com.syjun.demo.exceptions.InvalidReserveTimeException;
import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.model.Reservation;
import com.syjun.demo.repository.MeetingRoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author : red_bull
 * @date : 2019. 1. 12.   PM 8:29
 */
@Service
@Slf4j
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
    public List<Reservation> reserveMeetingRoom(String selectedDate, long roomId, String reserveName, long reserveTime, int repeatTime) throws ParseException, InvalidReserveTimeException {
        if(repeatTime > 0){
            return this.reserveRepeat(selectedDate, roomId, reserveName, reserveTime, repeatTime);
        }else{
            return Lists.newArrayList(this.reserve(selectedDate, roomId, reserveName, reserveTime));
        }
    }

    private List<Reservation> reserveRepeat(String selectedDate, long roomId, String reserveName, long reserveTime, int repeatTime) throws ParseException, InvalidReserveTimeException {
        log.info("반복 예약...");
        List<Reservation> reservationList = Lists.newArrayList();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date date = sdf.parse(selectedDate);

        for(int i=0; i<repeatTime; i++){
            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, (7 * i));
            reservationList.add(this.reserve(sdf.format(c.getTime()), roomId, reserveName, reserveTime));
        }

        return reservationList;
    }

    private Reservation reserve(String selectedDate, long roomId, String reserveName, long reserveTime) throws InvalidReserveTimeException {
        log.info("일반 예약...");
        DailyTimetable dailyTimetable = dailyTimetableService.getDailyTimetable(roomId, selectedDate);
        if(isAvailable(reserveTime, dailyTimetable.getTimetable())){
            dailyTimetable.setTimetable(dailyTimetable.getTimetable() + reserveTime);
            dailyTimetableService.updateDailyTimetable(dailyTimetable);

            Reservation reservation = new Reservation();
            reservation.setRoomId(roomId);
            reservation.setReserveDate(selectedDate);
            reservation.setReserveName(reserveName);
            reservation.setReserveTimetable(reserveTime);

            return reservationService.saveReservation(reservation);
        } throw new InvalidReserveTimeException();
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
