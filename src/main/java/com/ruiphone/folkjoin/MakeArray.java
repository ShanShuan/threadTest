package com.ruiphone.folkjoin;

import java.util.Random;

/**
 * Description:
 *
 * @author wang zifeng
 * @Date Create on 2019-07-26 17:28
 * @since version1.0 Copyright 2018 Burcent All Rights Reserved.
 */
public class MakeArray {
    public static final int ARRAY_LENGTH=4000;
    public  static int[] makeArray(){
        Random r=new Random();
        int[] result=new int[ARRAY_LENGTH];
        for (int i = 0; i <ARRAY_LENGTH ; i++) {
            result[i]=r.nextInt(ARRAY_LENGTH*3);
        }
        return  result;
    }
}
