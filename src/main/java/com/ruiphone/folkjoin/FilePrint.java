package com.ruiphone.folkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-29 11:50
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class FilePrint {
    private static  class FileDirFiles extends RecursiveAction{
        private File searchPath;

        @Override
        protected void compute() {
            List<FileDirFiles> subTaks=new ArrayList<>();
            File[] files = searchPath.listFiles();
            if(files!=null){
                for (File file : files) {
                    if(file.isDirectory()){
                        subTaks.add(new FileDirFiles(file.getAbsoluteFile()));
                    }else{
                        //如果是文件
                      if(file.getAbsolutePath().endsWith(".txt")){
                          System.out.println("File name is --------"+file.getAbsolutePath());
                      }
                    }
                }

                if(!subTaks.isEmpty()){
                    for (FileDirFiles subTask:invokeAll(subTaks)){
                        subTask.join();
                    }
                }
            }
        }

        public FileDirFiles(File searchPath) {
            this.searchPath = searchPath;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool=new ForkJoinPool();
        FileDirFiles task=new FileDirFiles(new File("E:/"));
        System.out.println("Task  is running ....");
        pool.execute(task);//异步
//        pool.invoke(task);//同步
        Thread.sleep(1);
        int oherWork=0;
        for (int i = 0; i <100 ; i++) {
            oherWork=oherWork+i;
        }
        System.out.println("Main thead do something....otherWork="+oherWork);
        task.join();
        System.out.println("Task end.");
    }
}
