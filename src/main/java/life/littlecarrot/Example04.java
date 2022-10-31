package life.littlecarrot;

import java.util.concurrent.CompletableFuture;

public class Example04 {
    public static void main(String[] args) {
        printThreadIdAndTag("小明进入餐厅，点了一份番茄炒蛋");
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() ->
        {
            printThreadIdAndTag("厨师炒菜");
            sleepMillis(200);
            return "番茄炒蛋";
        }).thenComposeAsync(dish -> CompletableFuture.supplyAsync(() -> {
            printThreadIdAndTag("服务员上菜");
            sleepMillis(100);
            return String.format("%s做好了", dish);
        }));
        printThreadIdAndTag("小明在玩手机");
        printThreadIdAndTag(String.format("%s，小明开吃", cf.join()));
    }

    private static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void printThreadIdAndTag(String tag) {
        System.out.printf("%s\t%s\t%s%n",Thread.currentThread().getId(), Thread.currentThread().getName(), tag);
    }
}
