package com.ruiphone.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * Description:  演示  countdownlatch  有5个初始化线程  6个扣除点
 *
 * @author wang zifeng
 * @Date Create on 2019-07-29 14:22
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class UserCountDownLatch {
    static CountDownLatch countDownLatch=new CountDownLatch(6);
    //初始化线程
    private static class InitThrea implements  Runnable{

        @Override
        public void run() {
            System.out.println("Thread_"+Thread.currentThread().getName()+"ready init work....");
            countDownLatch.countDown();//初始化线程  完成
            System.out.println("扣减点 为"+countDownLatch.getCount());
            for (int i = 0; i <2 ; i++) {
                System.out.println("Thread_"+Thread.currentThread().getName()+"countinue do its work....");
            }
        }
    }

    //业务线程
    private static  class  BusinessThrea implements Runnable{

        @Override
        public void run() {
            try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i <3 ; i++) {
                System.out.println("BusinessThrea_"+Thread.currentThread().getName()+"do  business-----");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new BusinessThrea()).start();
        new Thread(new Runnable() {
            @Override
            public void run() {

                countDownLatch.countDown();
                System.out.println("扣减点 为"+countDownLatch.getCount());
            }
        }).start();
        for (int i = 0; i <5 ; i++) {
            new Thread(new InitThrea()).start();
        }
        countDownLatch.countDown();
        System.out.println("扣减点 为"+countDownLatch.getCount());
        System.out.println("1111");
    }
}
