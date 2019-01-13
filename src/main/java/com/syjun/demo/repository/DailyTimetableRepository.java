package com.syjun.demo.repository;

import com.syjun.demo.model.DailyTimetable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : red_bull
 * @date : 2019. 1. 12.   PM 8:23
 */
public interface DailyTimetableRepository extends JpaRepository<DailyTimetable, Long>{

    DailyTimetable findOneByRoomIdAndDate(long roomId, String date);
}
