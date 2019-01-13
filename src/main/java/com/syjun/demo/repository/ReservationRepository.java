package com.syjun.demo.repository;

import com.syjun.demo.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author : red_bull
 * @date : 2019. 1. 12.   PM 8:23
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long>{
    List<Reservation> findAllByRoomIdAndReserveDate(long roomId, String reserveDate);
}
