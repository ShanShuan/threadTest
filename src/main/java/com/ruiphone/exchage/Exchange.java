package com.ruiphone.exchage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-29 15:21
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class Exchange {
    private static Exchanger<Set<String>> exchanger=new Exchanger<>();
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> set=new HashSet<String>();
                set.add("1");
                set.add("11");
                set.add("111");
                set.add("1111");
                try {
                    Set<String> xx = exchanger.exchange(set);

                    for(String one:xx){
                        System.out.println("线程1-------"+one);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String> set=new HashSet<String>();
                set.add("2");
                set.add("22");
                set.add("222");
                set.add("222");
                try {
                    Set<String> xx = exchanger.exchange(set);

                    for(String one:xx){
                        System.out.println("线程2------"+one);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
