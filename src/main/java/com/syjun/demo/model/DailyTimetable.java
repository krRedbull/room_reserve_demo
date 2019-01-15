package com.syjun.demo.model;

import com.syjun.demo.model.common.BaseDto;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Data
@Entity
@IdClass(DailyTimetableKey.class)
public class DailyTimetable extends BaseDto {
    @Id
    private long roomId;

    @Id
    private String date;

    private long timetable;

    @Transient
    public boolean[] getTimetableArray(){
        boolean[] booleans = new boolean[48];
        for(int i=0; i< Long.toBinaryString(this.timetable).length(); i++){
            booleans[i] = (this.timetable & (1<< i)) !=0;
        }
        return booleans;
    }

}
