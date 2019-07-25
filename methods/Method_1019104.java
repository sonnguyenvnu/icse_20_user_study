public static ExecutorService pool(){
  int cores=Runtime.getRuntime().availableProcessors();
  return Executors.newFixedThreadPool(cores + 1);
}
