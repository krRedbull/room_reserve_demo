package com.syjun.demo.service;

import com.syjun.demo.model.DailyTimetable;
import com.syjun.demo.repository.DailyTimetableRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Slf4j
public class DailyTimetableService {
    private final DailyTimetableRepository dailyTimetableRepository;

    @Autowired
    public DailyTimetableService(DailyTimetableRepository dailyTimetableRepository){
        this.dailyTimetableRepository = dailyTimetableRepository;
    }

    public DailyTimetable getDailyTimetable(long roomId, String date){
        DailyTimetable dailyTimetable = dailyTimetableRepository.findOneByRoomIdAndDate(roomId, date);

        if(Objects.isNull(dailyTimetable)){
            log.info("daily timetable does not exist. create daily timetable.");
            DailyTimetable newDailyTimetable = new DailyTimetable();
            newDailyTimetable.setRoomId(roomId);
            newDailyTimetable.setDate(date);
            newDailyTimetable.setTimetable(0L);

            return dailyTimetableRepository.save(newDailyTimetable);
        }else{
            return dailyTimetable;
        }
    }

    public DailyTimetable updateDailyTimetable(DailyTimetable dailyTimetable){
        return dailyTimetableRepository.save(dailyTimetable);
    }
}
