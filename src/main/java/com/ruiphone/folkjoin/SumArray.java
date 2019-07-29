package com.ruiphone.folkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * Description: 同步 fork join
 *
 * @author wang zifeng
 * @Date Create on 2019-07-26 17:42
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class SumArray {
    private static  class  SumTask extends RecursiveTask<Integer>{
        //阈值
        private final static int THRESHOLD=MakeArray.ARRAY_LENGTH/10;
        private int[] src;//实际要统计的数组
        private int fromIndex;
        private int toIndex;
        @Override
        protected Integer compute() {
            if(toIndex-fromIndex<THRESHOLD){
                int count=0;
                for (int i = fromIndex; i <=toIndex ; i++) {
                    count=count+src[i];
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                return count;
            }else{
                int mid=(toIndex+fromIndex)/2;
                SumTask left=new SumTask(src,fromIndex,mid);
                SumTask right=new SumTask(src,mid+1,toIndex);
                invokeAll(left,right);
                return left.join()+right.join();
            }

        }

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }
    }

    public static void main(String[] args) {
        //创建join连接池
        ForkJoinPool pool=new ForkJoinPool();

        int[] ints = MakeArray.makeArray();
        SumTask sumTask=new SumTask(ints,0,ints.length-1);
        long start = System.currentTimeMillis();
        pool.invoke(sumTask);
        long end = System.currentTimeMillis();
        System.out.println("spendtime:"+(end-start)/1000+"s----最后总和"+sumTask.join());

    }
}
