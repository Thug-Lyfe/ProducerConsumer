package producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author butwhole
 */
public class P extends Thread{
    
    private ArrayBlockingQueue<Long> q;
    private ArrayBlockingQueue<Long> S2;
    Long begin;
    public P(ArrayBlockingQueue<Long> q,ArrayBlockingQueue<Long> S2) {
        this.q = q;
        this.S2 = S2;
    }
    
    
    private long fib(long n) {
    if ((n == 0) || (n == 1)) {
      return n;
    } else {
      return fib(n - 1) + fib(n - 2);
    }
  }
    
    @Override
    public void run(){
        Long l;
        while((l = q.poll()) != null){
            begin = l;
            Long sum = fib(l);
            try {
                S2.put(sum);
                System.out.println(begin + ": " + sum);
            } catch (InterruptedException ex) {
                Logger.getLogger(P.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    }
    

