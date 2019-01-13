package com.syjun.demo.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ReserveMeetingRoomParam {

    @NotNull
    String reserveName;
    @NotNull
    long reserveTime;
    Integer repeatTime;
}
