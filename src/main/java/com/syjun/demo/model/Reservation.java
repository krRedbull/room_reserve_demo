package com.syjun.demo.model;

import com.syjun.demo.model.common.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Reservation extends BaseDto {

    @Id
    @GeneratedValue
    private long id;

    private long roomId;

    private String reserveName;

    private long reserveTimetable;
}
