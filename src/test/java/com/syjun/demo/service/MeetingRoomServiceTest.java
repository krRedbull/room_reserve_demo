package com.syjun.demo.service;

import com.syjun.demo.model.MeetingRoom;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MeetingRoomServiceTest {

    @Autowired
    MeetingRoomService meetingRoomService;

    @Test
    public void setMeetingRoom(){
        String roomName = "test 1";
        System.out.println(meetingRoomService.setMeetingRoom(roomName));
    }

    @Test
    public void setAndGetMeetingRoom(){
        String roomName = "test 2";
        MeetingRoom meetingRoom = meetingRoomService.setMeetingRoom(roomName);

        System.out.println(""+meetingRoomService.getMeetingRoom(meetingRoom.getId()));
    }

    @Test
    public void getMeetingRoomList(){
        for(int i=3; i< 100; i++){
            meetingRoomService.setMeetingRoom("room "+i);
        }

        System.out.println(meetingRoomService.getMeetingRoomList());
    }
}
