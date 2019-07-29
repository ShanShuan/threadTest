package com.ruiphone.waitNotify;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-26 15:00
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class Express {
    public static  final  String CITY="shanghai";
    private String site;
    private int km;

    public Express() {
    }

    public Express(String site, int km) {
        this.site = site;
        this.km = km;
    }

     public synchronized  void changeKm(){
        this.km=101;
//        notify();

        notifyAll();
     }
    public synchronized  void changeSite(){
        this.site="Beijing";
//        notify();
        notifyAll();
    }

    public synchronized void waitKm(){
        while(this.km<=100){
            try {
                wait();
                System.out.println("ckeck km....  thread ["+Thread.currentThread().getName()+"] is being notify");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("current km is "+this.km);
    }


    public synchronized  void waitSite(){
        while(CITY.equals(this.site)){
            try {
                wait();
                System.out.println("ckeck site.... thread ["+Thread.currentThread().getName()+"] is being notify");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("current site is "+this.site);
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
