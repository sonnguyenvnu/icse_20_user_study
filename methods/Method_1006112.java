public static <V>BackgroundTask<V> wrap(Callable<V> callable){
  return new BackgroundTask<V>(){
    @Override protected V call() throws Exception {
      return callable.call();
    }
  }
;
}
