private static void test1() throws InterruptedException {
  ScheduledExecutorService executor=Executors.newScheduledThreadPool(1);
  Runnable task=() -> System.out.println("Scheduling: " + System.nanoTime());
  int delay=3;
  ScheduledFuture<?> future=executor.schedule(task,delay,TimeUnit.SECONDS);
  TimeUnit.MILLISECONDS.sleep(1337);
  long remainingDelay=future.getDelay(TimeUnit.MILLISECONDS);
  System.out.printf("Remaining Delay: %sms\n",remainingDelay);
}
