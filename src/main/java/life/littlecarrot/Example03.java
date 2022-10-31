package life.littlecarrot;

import java.util.concurrent.CompletableFuture;

public class Example03 {
    public static void main(String[] args) {
        printThreadIdAndTag("小明进入餐厅，点了一份番茄炒蛋");
        CompletableFuture<Object> cf = CompletableFuture.supplyAsync(() ->
        {
            printThreadIdAndTag("厨师炒菜");
            sleepMillis(200);
            throw new RuntimeException("厨房着火了");
        }).exceptionally(e -> String.format("%s，小明不吃了", e.getCause().getMessage()));
        printThreadIdAndTag("小明在玩手机");
        printThreadIdAndTag(cf.join().toString());
    }

    private static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printThreadIdAndTag(String tag) {
        System.out.printf("%s\t%s%n", Thread.currentThread().getId(), tag);
    }
}
