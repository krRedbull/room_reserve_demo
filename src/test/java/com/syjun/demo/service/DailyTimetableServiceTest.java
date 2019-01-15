package com.syjun.demo.service;

import com.syjun.demo.model.DailyTimetable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DailyTimetableServiceTest {
    @Autowired
    DailyTimetableService dailyTimetableService;

    @Test
    public void getDailyTimetable(){
        System.out.println(dailyTimetableService.getDailyTimetable(3,"20180115"));
    }

    @Test
    public void updateDailyTimetable(){
        DailyTimetable dailyTimetable = dailyTimetableService.getDailyTimetable(3,"20180115");
        dailyTimetable.setTimetable(5);
        System.out.println(dailyTimetableService.updateDailyTimetable(dailyTimetable));
    }
}
