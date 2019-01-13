package com.syjun.demo.model;

import com.syjun.demo.model.common.BaseDto;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Arrays;

@Data
@Entity
public class DailyTimetable extends BaseDto {

    @Id
    @GeneratedValue
    private long id;

    private long roomId;

    private String date;

    private long timetable;

    public Boolean[] getTimetableArray(){
        Boolean[] booleans = new Boolean[48];
        Arrays.fill(booleans, false);

        for(int i=0; i< Long.bitCount(this.timetable); i++){
            booleans[i] = (this.timetable & (1<< i)) !=0;
        }

        return booleans;
    }

}
