private static ThreadFactory build(ThreadFactory wrappedThreadFactory,@Nullable String namePrefix,@Nullable String name,boolean daemonThreads,@Nullable UncaughtExceptionHandler uncaughtExceptionHandler,@Nullable Integer priority){
  return new ThreadFactory(){
    @Override public Thread newThread(    @Nullable Runnable runnable){
      Thread thread=wrappedThreadFactory.newThread(runnable);
      if (namePrefix != null && name != null) {
        thread.setName(String.format("%s-%s-%d",namePrefix,name,threadCounter.getAndIncrement()));
      }
 else       if (namePrefix != null && name == null) {
        thread.setName(String.format("%s-%s",namePrefix,thread.getName()));
      }
 else       if (namePrefix == null && name != null) {
        thread.setName(String.format("%s-%d",name,threadCounter.getAndIncrement()));
      }
      thread.setDaemon(daemonThreads);
      if (uncaughtExceptionHandler != null) {
        thread.setUncaughtExceptionHandler(uncaughtExceptionHandler);
      }
      if (priority != null) {
        thread.setPriority(priority);
      }
      return thread;
    }
  }
;
}
