package com.syjun.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(Long.toBinaryString(-9223372036854775808L));
        System.out.println(Long.toBinaryString(-9223372036854775808L).length());
    }

    @Test
    public void bitOperation(){
        long a = 281474976710655L;
        long b = 17L;

        System.out.println(Long.toBinaryString(a));
        System.out.println(Long.toBinaryString(b));

        long c = a & b;

        System.out.println(Long.toBinaryString(c));

        System.out.println(Long.bitCount(a));

        Boolean[] booleans = new Boolean[48];
        Arrays.fill(booleans, false);

        for(int i=0; i< Long.bitCount(a); i++){
            booleans[i] = (a & (1<< i)) !=0;
        }

        StringBuffer stringBuffer = new StringBuffer();

        Arrays.stream(booleans).forEach(d ->{
            if(d){
                stringBuffer.append(1);
            } else {
                stringBuffer.append(0);
            }
        });

        System.out.println(stringBuffer.toString());
        System.out.println(stringBuffer.toString().length());


    }

}

