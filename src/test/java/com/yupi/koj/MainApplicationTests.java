package com.yupi.koj;

import com.yupi.koj.config.WxOpenConfig;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 *
 * @author knowei
 * 
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Test
    void contextLoads() {
        System.out.println(wxOpenConfig);
    }

    public static void main(String[] args) {
        DemoThread demoThread = new DemoThread();
        Thread thread = new Thread(demoThread);
        thread.start();

        new Thread(new DemoRun(), "a").start();
    }

}

class DemoThread extends Thread{
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running...");
    }
}

class DemoRun implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " running..");
    }
}


