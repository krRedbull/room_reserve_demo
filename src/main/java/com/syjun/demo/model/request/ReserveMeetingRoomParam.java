package com.syjun.demo.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReserveMeetingRoomParam {

    String reserveName;
    long reserveTime;
    Integer repeatTime;
}
