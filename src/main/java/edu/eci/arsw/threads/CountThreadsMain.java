/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author hcadavid
 */
public class CountThreadsMain {
    
    public static void main(String a[]){
//        Thread w = new Thread(new CountThread(2,4)) ;

        Thread j = new Thread(new CountThread(0,99));
        Thread l = new Thread(new CountThread(99,199));
        Thread s = new Thread(new CountThread(200, 299));
//        w.start();
        j.start();
        l.start();
        s.start();
    }
    
}
