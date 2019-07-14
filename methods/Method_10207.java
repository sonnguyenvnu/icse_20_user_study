private static void testLock(){
  count=0;
  ExecutorService executor=Executors.newFixedThreadPool(2);
  IntStream.range(0,NUM_INCREMENTS).forEach(i -> executor.submit(Lock1::increment));
  ConcurrentUtils.stop(executor);
  System.out.println(count);
}
