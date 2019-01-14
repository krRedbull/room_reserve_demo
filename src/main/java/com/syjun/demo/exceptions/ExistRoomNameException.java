package com.syjun.demo.exceptions;

public class ExistRoomNameException extends RuntimeException {
    public ExistRoomNameException(){
        super("이미 등록된 회의실입니다. 다른 이름을 입력해주세요.");
    }
}
