package com.syjun.demo.service;

import com.syjun.demo.model.Reservation;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationServiceTest {
    @Autowired
    ReservationService reservationService;

    @Test
    public void saveReservation(){
        Reservation reservation = new Reservation();
        reservation.setReserveDate("20190115");
        reservation.setReserveName("ASDF");
        reservation.setReserveTimetable(4576);
        reservation.setRoomId(3);

        System.out.println(reservationService.saveReservation(reservation));
    }

    @Test
    public void getReservationOfMeetingRoom(){
        Reservation reservation = new Reservation();
        reservation.setReserveDate("20190115");
        reservation.setReserveName("ASDF");
        reservation.setReserveTimetable(1);
        reservation.setRoomId(3);
        reservationService.saveReservation(reservation);

        Reservation reservation1 = new Reservation();
        reservation1.setReserveDate("20190115");
        reservation1.setReserveName("ASDF");
        reservation1.setReserveTimetable(16);
        reservation1.setRoomId(3);
        reservationService.saveReservation(reservation1);

        Reservation reservation2 = new Reservation();
        reservation2.setReserveDate("20190115");
        reservation2.setReserveName("ASDF");
        reservation2.setReserveTimetable(34);
        reservation2.setRoomId(3);
        reservationService.saveReservation(reservation2);

        System.out.println(reservationService.getReservationOfMeetingRoom(3, "20190115"));
        System.out.println(reservationService.getReservationOfMeetingRoom(3, "20190115").size());

    }
}
