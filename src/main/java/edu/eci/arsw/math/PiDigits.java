package edu.eci.arsw.math;

import edu.eci.arsw.threads.ThreadPiDigits;

import java.util.ArrayList;

///  <summary>
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {

    private static int DigitsPerSum = 8;
    private static double Epsilon = 1e-17;

    
    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public static byte[] getDigits(int start, int count, int N) throws InterruptedException {

        ArrayList<ThreadPiDigits> listThreads = new ArrayList<ThreadPiDigits>();

        int numbersPerThread = count / N ;
        int numbersExtraThread = count % N;



        listThreads.add(new ThreadPiDigits(start,(numbersPerThread + numbersExtraThread)));
        N--;

        for (int i= 0; i < N; i++ ) {
            listThreads.add(new ThreadPiDigits(start,numbersPerThread));
            start += numbersPerThread;
        }

        for (ThreadPiDigits t: listThreads ) {
            t.start();
        }



        try {
            for (ThreadPiDigits t: listThreads ) {
                t.join();
            }




        } catch (Exception e) {
            System.out.println("Thread error.");
        }




        return digits;
    }


}
