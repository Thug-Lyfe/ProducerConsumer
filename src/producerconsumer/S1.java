/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author butwhole
 */
public class S1 extends Thread {

    private ArrayBlockingQueue<Long> q = new ArrayBlockingQueue(20);
    private ArrayBlockingQueue<Long> s1 = new ArrayBlockingQueue(20);
    private int Threads;

    public S1(Long[] list ,int Threads) {
        for (Long m : list) {
            q.add(m);
        }
        this.Threads = Threads;

    }

    public void run() {
        List<P> list = new ArrayList();
        long start = System.nanoTime();
        for (int i = 0; i < Threads; i++) {
            list.add(new P(q, s1));
            list.get(i).start();

        }
        C1 c1 = new C1(s1);
        c1.start();

        for (int i = 0; i < Threads; i++) {
            try {
                list.get(i).join();
            } catch (InterruptedException ex) {
                Logger.getLogger(S1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        long end = System.nanoTime();
        c1.setRun(false);
        System.out.println("sum is: " + c1.getSum());
        System.out.println("Time taken: " + (end-start));
        try {
            s1.put(0L);
        } catch (InterruptedException ex) {
            Logger.getLogger(S1.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        Long[] list = {4L, 5L, 8L, 12L, 21L, 22L, 34L, 35L, 36L, 37L, 42L,};
        S1 s1 = new S1(list,3);
        s1.start();

    }

}
