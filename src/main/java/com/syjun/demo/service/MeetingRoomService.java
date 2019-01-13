package com.syjun.demo.service;

import com.syjun.demo.model.MeetingRoom;
import com.syjun.demo.repository.MeetingRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingRoomService {
    private final MeetingRoomRepository meetingRoomRepository;

    @Autowired
    public MeetingRoomService(MeetingRoomRepository meetingRoomRepository){
        this.meetingRoomRepository = meetingRoomRepository;
    }

    public MeetingRoom getMeetingRoom(long roomId){
        return meetingRoomRepository.findOneById(roomId);
    }

    public MeetingRoom setMeetingRoom(String roomName){
        MeetingRoom meetingRoom = new MeetingRoom();
        meetingRoom.setRoomName(roomName);
        return meetingRoomRepository.save(meetingRoom);
    }

    public List<MeetingRoom> getMeetingRoomList(){
        return meetingRoomRepository.findAll();
    }
}
