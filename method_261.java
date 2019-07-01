protected <T>CompletableFuture<T> _XXXXX_(Callable<T> callable){
  return executeIO(readIOScheduler,callable);
}