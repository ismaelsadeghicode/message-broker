package com.javatar.kafka.consumer;

/**
 * Copyright 2020 (C) fwutech.com
 *
 * @author : Esmaeil Sadeghi {@link "mailto:e.sadeghi@fwutech.com"}
 */
public class Main {
    public static void main(String[] args) {
        int b=0,a=2;
        for (int i = 2; i <5; b++) {
            for (int c = 1; c <5; c+=2) {
                a*=3;
                a=a*b+c;
            }
        } 

        System.out.println("sss");
        System.out.println(a);
    }

//    static boolean isnotPi(int number){
//        for (int i = 2; i < number; i++) {
//            if(number%i==0)
//                return false;
//        }
//        return true;
//    }

}
