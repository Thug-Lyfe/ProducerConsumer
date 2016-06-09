/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package producerconsumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author butwhole
 */
public class C1 extends Thread{
    private ArrayBlockingQueue<Long> s1;
    private Long sum = 0L;
    private boolean run = true;
    public C1(ArrayBlockingQueue<Long> s1) {
        this.s1 = s1;
    }

    public Long getSum() {
        return sum;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
    
    
    
    public void run(){
        Long res;
        while(run){
            try {
                if((res = s1.take()) != null){
                    sum = sum + res;
                }
                
            } catch (InterruptedException ex) {
                Logger.getLogger(C1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
