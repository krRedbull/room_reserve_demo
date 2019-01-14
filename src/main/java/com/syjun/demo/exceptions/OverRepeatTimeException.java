package com.syjun.demo.exceptions;

public class OverRepeatTimeException extends RuntimeException{
    public OverRepeatTimeException(){
        super("반복 예약 횟수가 초과되1었습니다.");
    }
}
