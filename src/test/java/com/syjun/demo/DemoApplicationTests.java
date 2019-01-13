package com.syjun.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        long a = 47534L;
        long b = 17L;

        System.out.println(Long.toBinaryString(a));
        System.out.println(Long.toBinaryString(b));

        long c = a & b;

        System.out.println(Long.toBinaryString(c));


    }

}

