package com.liuh.threadtest;

/**
 * Date: 2018/6/11 09:17
 * Description:
 */

public class MyThread extends Thread {

    private final Object lock = new Object();
    private boolean pause = false;

    /**
     * 暂停线程
     */
    void pauseThread() {
        pause = true;
    }

    /**
     * 恢复线程运行
     */
    void resumeThread() {
        pause = false;
        synchronized (lock) {
            lock.notifyAll();
        }
    }

    /**
     * 这个方法只能在run方法里面调用，不然会阻塞主线程，导致页面无响应
     */
    void onPause() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void run() {
        super.run();
        int index = 0;
        while (true) {
            while (pause) {
                onPause();
            }
            try {
                System.out.println("-------index : " + index);
                Thread.sleep(50);
                ++index;
            } catch (InterruptedException e) {
//                e.printStackTrace();
                //捕获到异常后，执行break跳出循环
                break;
            }
        }
    }
}
