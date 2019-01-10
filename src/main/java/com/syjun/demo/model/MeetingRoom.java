package com.syjun.demo.model;

import lombok.Data;

import javax.persistence.Entity;

@Data
public class MeetingRoom {
    private long id;
    private String roomName;
}
