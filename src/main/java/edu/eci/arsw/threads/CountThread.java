/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

public class CountThread implements Runnable{


    @Override
    public void run() {
        System.out.println("thread is running..");
        counter(5,30);
    }

    public void counter(int A, int B){

        for (int i = A; i < B; i++) {
            System.out.println(i);

        }

    }


}
