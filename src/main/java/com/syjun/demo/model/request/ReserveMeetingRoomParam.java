package com.syjun.demo.model.request;

import lombok.Data;

@Data
public class ReserveMeetingRoomParam {

    String reserveName;
    long reserveTime;
    Integer repeatTime;
}
