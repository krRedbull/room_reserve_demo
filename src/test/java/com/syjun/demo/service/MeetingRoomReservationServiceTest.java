package com.syjun.demo.service;

import com.syjun.demo.exceptions.InvalidReserveTimeException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingRoomReservationServiceTest {
    @Autowired
    MeetingRoomReservationService meetingRoomReservationService;

    @Test
    public void reserveTest() throws ParseException {
        meetingRoomReservationService.reserveMeetingRoom("20190113", 1, "asdf", 554, 0);
    }

    @Test
    public void reserveRepeatTest() throws ParseException, InvalidReserveTimeException {
        meetingRoomReservationService.reserveMeetingRoom("20190114", 1, "asdf", 554, 4);
    }
}
