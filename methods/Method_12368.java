private static ThreadPoolTaskScheduler registrationTaskScheduler(){
  ThreadPoolTaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
  taskScheduler.setPoolSize(1);
  taskScheduler.setRemoveOnCancelPolicy(true);
  taskScheduler.setThreadNamePrefix("registrationTask");
  return taskScheduler;
}
