private static void test1(){
  ForkJoinPool commonPool=ForkJoinPool.commonPool();
  System.out.println(commonPool.getParallelism());
}
