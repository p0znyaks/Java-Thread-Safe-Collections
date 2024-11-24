package Thread_safe;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueEx {
    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> queue =
                new ArrayBlockingQueue<>(4);
        System.out.println(queue);

        Runnable producerRunnable = () -> {
          for (int i = 1; i <= 10; i++) {
              try {
                  queue.put(i);
                  System.out.println("Producer добавил - " + i);
                  Thread.sleep(3000);
              } catch (InterruptedException e) {
                  throw new RuntimeException(e);
              }
          }
        };

        Runnable consumerRunnable = () -> {
            while (true) {
                try {
                    int taken = queue.take();
                    System.out.println("Consumer забрал - " + taken);
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread producer = new Thread(producerRunnable);
        Thread consumer = new Thread(consumerRunnable);
        producer.start();
        consumer.start();
    }
}
