package com.syjun.demo.service;

import com.syjun.demo.model.Reservation;
import com.syjun.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository){
        this.reservationRepository = reservationRepository;
    }

    public Reservation saveReservation(Reservation reservation){
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationOfMeetingRoom(long roomId, String reserveDate){
        return reservationRepository.findAllByRoomIdAndReserveDate(roomId, reserveDate);
    }
}
