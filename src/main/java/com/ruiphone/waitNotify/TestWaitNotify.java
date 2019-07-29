package com.ruiphone.waitNotify;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-26 15:15
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class TestWaitNotify {
    private static Express express=new Express(Express.CITY,1);

    private static class  CkeckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }


    private static class  CkeckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <4 ; i++) {
                new CkeckKm().start();
        }

        for (int i = 0; i <4 ; i++) {
            new CkeckSite().start();
        }
        try {
            Thread.sleep(3000);
//            express.changeKm();
            express.changeSite();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
