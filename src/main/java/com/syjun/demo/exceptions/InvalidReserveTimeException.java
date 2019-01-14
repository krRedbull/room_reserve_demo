package com.syjun.demo.exceptions;

public class InvalidReserveTimeException extends RuntimeException{
    public InvalidReserveTimeException() {
        super("이미 예약이 되었습니다.");
    }
}
