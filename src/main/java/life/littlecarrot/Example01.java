package life.littlecarrot;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class Example01 {
    public static void main(String[] args) {
        System.out.println("主线程开始");
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("这是一个子任务");
            return "子任务完成";
        });
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(futureTask);
        System.out.println("子任务开始");
        try {
            String result = futureTask.get();
            System.out.println("子任务返回" + result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            Throwable cause = e.getCause();
            cause.printStackTrace();
        }
        executorService.shutdown();
        System.out.println("主线程完成");
    }
}
