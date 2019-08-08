package com.ruiphone.pool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import static java.lang.Thread.currentThread;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-08-08 11:05
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class FixedSzieThreadPool {
    //仓库存放任务
    private BlockingQueue blockingQueue;
    //一大堆线程
    private List<Thread> works;

    /**
     *
     * @param theadSize 线程池大小
     * @param runableSize 任务仓库大小
     */
    public FixedSzieThreadPool(int theadSize,int runableSize) {
        if(runableSize==0||runableSize<0){
            blockingQueue=new LinkedBlockingDeque(20);
        }else {
            blockingQueue = new LinkedBlockingDeque(runableSize);
        }
        if(theadSize==0||theadSize<0){
            works= Collections.synchronizedList(new ArrayList<Thread>(20));
        }else {
            works= Collections.synchronizedList(new ArrayList<Thread>(theadSize));
        }
        int  workmm;
        if(theadSize==0||theadSize<0){
            workmm=20;
        }else{
            workmm=theadSize;
        }
        for (int i = 0; i <workmm ; i++) {
            Work work=new Work();
            work.setName("线程"+(i+1));
            work.start();
            works.add(work);
        }
    }


    //关闭线程池
    /**
     * 1.队列需要停止进入任务
     * 2.一但我们的仓库还有任务  就需要去执行完毕
     * 3.去仓库 拿任务，就不应该阻塞
     * 4.一旦有任务  阻塞，就中断它
     */
    private volatile  boolean  isworking=true;
    public  void  shutdown(){
        this.isworking=false;
        for (Thread t:works) {
            if(Thread.State.BLOCKED.equals(t.getState())){
                t.interrupt();
            }
        }
    }


    /**
     * 往任务仓库  添加任务
     * @param runnable
     */
    public void  submitTaak(Runnable runnable){
        if(isworking) {
            blockingQueue.offer(runnable);
        }
    }

    class Work extends Thread{
        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()) {
                while (blockingQueue.size() > 0 || isworking) {
                    Runnable take = null;
                    try {
                        if (isworking) {
                            take = (Runnable) blockingQueue.take();
                        } else {
                            take = (Runnable) blockingQueue.poll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (take != null) {
                        take.run();
                        System.out.println(currentThread().getName() + "正在运行" + take.toString());
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        FixedSzieThreadPool fixedSzieThreadPool = new FixedSzieThreadPool(10, 6);

        for (int i = 0; i <1000 ; i++) {
                System.out.println("添加第"+(i+1)+"任务");
//                Thread.sleep(200);


            final int finalI = i;
            fixedSzieThreadPool.submitTaak(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("running...");
                }

                @Override
                public String toString() {
                    return "第个"+(finalI +1)+"任务";
                }
            });
        }
        Thread.sleep(3000);
        fixedSzieThreadPool.shutdown();
    }
}
