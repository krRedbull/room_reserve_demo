package com.syjun.demo.controller;

import com.syjun.demo.model.common.TestModel;
import com.syjun.demo.repository.TestRepository;
import com.syjun.demo.service.MeetingRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RequestMapping("/demo")
@RestController
public class DemoController {

    @Autowired
    MeetingRoomService meetingRoomService;


    @GetMapping("/health")
    public String healthCheck(){
        return "OK";
    }

    @PostMapping("/initialize")
    public String initDemo(){

        for(int i=0;i<3;i++){
            meetingRoomService.setMeetingRoom("meeting_room_"+i);
        }
        return "OK";
    }
}
