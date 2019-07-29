package com.ruiphone.UserCycliBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-29 14:56
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class UserCycliBarrier {
    private static CyclicBarrier cyclicBarrier=new CyclicBarrier(5);

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new SubThread()).start();
        }

    }

    private static class SubThread implements Runnable{

        @Override
        public void run() {
            String  name=Thread.currentThread().getName();
            System.out.println(name+"---is  await()");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(name+"is do its business。。。。");

        }
    }
}
