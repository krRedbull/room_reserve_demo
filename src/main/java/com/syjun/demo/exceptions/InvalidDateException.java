package com.syjun.demo.exceptions;

import org.springframework.beans.factory.annotation.Value;

public class InvalidDateException extends RuntimeException{
    public InvalidDateException(){
        super("예약 범위를 초과하였습니다. 예약 범위를 확인하세요.");
    }
}
