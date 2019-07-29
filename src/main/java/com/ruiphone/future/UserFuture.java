package com.ruiphone.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-29 15:43
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class UserFuture {
    private static class UserCallAble implements Callable<Integer>{

        @Override
        public Integer call() throws Exception {
            System.out.println("开始计算");
            Thread.sleep(2000);
            Integer sum=0;
            for (int i = 0; i <5000 ; i++) {
                sum=sum+i;
            }
            System.out.println("计算结束");
            return sum;
        }
    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        UserCallAble userCallAble=new UserCallAble();
        FutureTask<Integer> futureTask=new FutureTask<>(userCallAble);
        new Thread(futureTask).start();
        System.out.println("111");
        System.out.println("最后计算结果："+futureTask.get());
        System.out.println("222");
    }
}
