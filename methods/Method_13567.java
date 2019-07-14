private static ThreadPoolTaskScheduler getTaskScheduler(){
  ThreadPoolTaskScheduler taskScheduler=new ThreadPoolTaskScheduler();
  taskScheduler.initialize();
  return taskScheduler;
}
