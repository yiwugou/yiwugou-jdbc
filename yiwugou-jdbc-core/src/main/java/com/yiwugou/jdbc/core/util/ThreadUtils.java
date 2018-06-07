package com.yiwugou.jdbc.core.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadUtils {
    public static <T> T threadCancel(Callable<T> callable, long milliSeconds) {
        final ExecutorService exec = Executors.newFixedThreadPool(1);
        Future<T> future = exec.submit(callable);
        try {
            return future.get(milliSeconds, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            future.cancel(true);//必须代
        }
        return null;
    }
}
