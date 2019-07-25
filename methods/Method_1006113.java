public static BackgroundTask<Void> wrap(Runnable runnable){
  return new BackgroundTask<Void>(){
    @Override protected Void call() throws Exception {
      runnable.run();
      return null;
    }
  }
;
}
