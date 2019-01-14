package com.syjun.demo.model;

import com.syjun.demo.model.common.BaseDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class MeetingRoom extends BaseDto {

    @Id
    @GeneratedValue
    private long id;

    private String roomName;
}
