package com.ruiphone.folkjoin;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-26 17:32
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class SumNomal {
    public static void main(String[] args) throws InterruptedException {
        int count=0;

        int[] ints = MakeArray.makeArray();

        long start=System.currentTimeMillis();
        for (int anInt : ints) {
            Thread.sleep(1);
            count=count+anInt;
        }
        System.out.println("the count is "+count+"--spendtime is "
                +(System.currentTimeMillis()-start)/1000+"s");
    }
}


