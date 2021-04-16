package Threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadFactory;

public class SimpleThreadFactory implements ThreadFactory {
    private Thread[] threadPool;

    public static SimpleThreadFactory single_instance = null;

    public static SimpleThreadFactory getInstance(){
        if (single_instance == null){
            single_instance = new SimpleThreadFactory();
        }

        return single_instance;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r);
    }

    public SimpleThreadFactory(){
        this.threadPool = new Thread[4];
        this.create();
    }


    public SimpleThreadFactory(Thread[] pool){
        this.threadPool = pool;
    }

    public Thread[] create() {
        Thread[] pool = new Thread[4];
        return pool;
    }

    public Thread getThread(Runnable r) {
        int count = 0;
        for (int i = 0; i < 4; i++){
            if (threadPool[i]==null){
                threadPool[i] = newThread(r);
                return threadPool[i];
            }
            else
                count++;
        }

        if (count == 4)
            System.out.println("Sorry out of threads right now, returning null");
        return null;
    }


    public boolean releaseConnection(Thread thread) {
        for (int i = 0; i < 4; i++){
            if (threadPool[i] == thread){
                threadPool[i] = null;
                return true;
            }
        }
        return false;
    }

    public int getSize() {
        int count = 0;
        for (int i = 0; i < threadPool.length; i++){
            if (threadPool[i] != null){
                count++;
            }
        }
        return count;
    }
}
