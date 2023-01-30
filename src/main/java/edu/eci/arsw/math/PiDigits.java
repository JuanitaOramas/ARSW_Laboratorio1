package edu.eci.arsw.math;
import java.util.ArrayList;
import java.util.List;
import edu.eci.arsw.threads.*;
///
///  An implementation of the Bailey-Borwein-Plouffe formula for calculating hexadecimal
///  digits of pi.
///  https://en.wikipedia.org/wiki/Bailey%E2%80%93Borwein%E2%80%93Plouffe_formula
///  *** Translated from C# code: https://github.com/mmoroney/DigitsOfPi ***
///  </summary>
public class PiDigits {
    private int DigitsPerSum = 8;
    private double Epsilon = 1e-17;
    private List<ThreadPiDigits> myListThread = new ArrayList<ThreadPiDigits>();
    private byte[] byteList;
    /**
     * Returns a range of hexadecimal digits of pi.
     * @param start The starting location of the range.
     * @param count The number of digits to return
     * @return An array containing the hexadecimal digits.
     */
    public byte[] getDigits(int start, int count, int N) {
        int numberPerThread = count / N;
        byteList = new byte[count];
        int aux = 0;
        if (count % N != 0) aux =count % N ;
        myListThread.add(new ThreadPiDigits(start,numberPerThread + aux));
        N--;
        start += aux;
        // System.out.println("VALOR start: " + start);
        for (int i = 0; i < N; i++) {
            myListThread.add(new ThreadPiDigits(start + numberPerThread,numberPerThread));
            start +=numberPerThread;
            // System.out.println("VALOR start: " + start + ", VALOR PER THREAD: " + (numberPerThread + aux) );
        }
        for (Thread t : myListThread) {
            t.start();
        }
        try {
            int i = 0;
            for (ThreadPiDigits t : myListThread) {
                t.join();
                for (byte b : t.getMyListDigits()) {
                    byteList[i]= b;
                    i++;
                }
            }
        } catch (Exception e) {
            System.out.println("Error in thread");
        }
        byte[] digits = byteList;
        return digits;
    }
}