private static void testNonSyncIncrement(){
  count=0;
  ExecutorService executor=Executors.newFixedThreadPool(2);
  IntStream.range(0,NUM_INCREMENTS).forEach(i -> executor.submit(Synchronized1::increment));
  ConcurrentUtils.stop(executor);
  System.out.println("NonSync: " + count);
}
