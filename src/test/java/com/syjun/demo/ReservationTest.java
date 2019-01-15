package com.syjun.demo;

import com.google.common.collect.Lists;
import com.syjun.demo.service.MeetingRoomReservationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;
import java.util.concurrent.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReservationTest {
    @Autowired
    MeetingRoomReservationService meetingRoomReservationService;

    @Test
    public void 쓰레드여러개에서동시예약테스트() {
        ExecutorService executorService = Executors.newFixedThreadPool(30);
        List<Callable<String>> callableList = Lists.newArrayList();

        for(int i=0; i<60; i++){
            Callable<String> task = () -> {
                try{
                    meetingRoomReservationService.reserveMeetingRoom("20180119", 1L, "Thread_number", 16L, 1);
                } catch (Exception e){
                    return "fail :: " + Thread.currentThread().getName() + " :: with exception :: " + e.getMessage();
                }
                return "success :: "+ Thread.currentThread().getName();
            };
            callableList.add(task);
        }

        List<Future<String>> futures = Lists.newArrayList();

        try {
            futures = executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        List<String> successResult = Lists.newArrayList();
        List<String> failResult = Lists.newArrayList();

        futures.forEach(stringFuture -> {
            try {
                if(stringFuture.get().contains("success ::")){
                    successResult.add(stringFuture.get());
                }else{
                    failResult.add(stringFuture.get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        });

        executorService.shutdownNow();

        successResult.forEach(s -> {
            System.out.println(s);
        });

        failResult.forEach(s -> {
            System.out.println(s);
        });

        // Thread 경쟁에서 1개만 들어가야함
        assert successResult.size()==1;

    }

}
