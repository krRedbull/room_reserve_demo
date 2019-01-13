package com.syjun.demo.model;

import com.syjun.demo.model.common.BaseDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
public class DailyTimetable extends BaseDto {

    @Id
    @GeneratedValue
    private long id;

    private long roomId;

    private String date;

    private long timetable;

}
