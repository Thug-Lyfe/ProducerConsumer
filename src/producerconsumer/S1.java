/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author butwhole
 */
public class S1 {

    private static ArrayBlockingQueue<Long> q = new ArrayBlockingQueue(100);
    private static ArrayBlockingQueue<Long> s1 = new ArrayBlockingQueue(100);

    public static long run(Long[] list, int Threads) {
        long start = System.nanoTime() / 1000_000_0;
        q.addAll(Arrays.asList(list));
        List<P> ThreadList = new ArrayList();

        for (int i = 0; i < Threads; i++) {
            ThreadList.add(new P(q, s1));
            ThreadList.get(i).start();

        }
        C1 c1 = new C1(s1);
        c1.start();

        for (int i = 0; i < Threads; i++) {
            try {
                ThreadList.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(S1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        long end = System.nanoTime() / 1000_000_0;
        c1.setRun(false);
        System.out.println("sum is: " + c1.getSum());
        System.out.println("Time taken: " + (end - start));
        try {
            s1.put(0L);
        } catch (InterruptedException ex) {
            Logger.getLogger(S1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return end-start;
    }

    public static void main(String[] args) {
        Long[] list = {1L, 2L, 3L, 4L, 5L, 6L, 7L, 8L, 9L, 10L, 11L, 12L, 21L, 22L, 34L, 35L, 36L, 37L, 38L, 39L, 40L, 41L, 42L};
        ArrayList<Long> res = new ArrayList();
        for (int i = 0; i < 8; i++) {
            res.add(run(list,i));
        }
        for (int i = 0; i < 8; i++) {
            System.out.println("Time Taken (s"+i+"): " + res.get(i));
        }
        

    }

}
