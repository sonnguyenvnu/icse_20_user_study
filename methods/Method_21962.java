private Properties getBaseQuartzProperties(){
  Properties result=new Properties();
  result.put("org.quartz.threadPool.class",org.quartz.simpl.SimpleThreadPool.class.getName());
  result.put("org.quartz.threadPool.threadCount","1");
  result.put("org.quartz.scheduler.instanceName",liteJobConfig.getJobName());
  result.put("org.quartz.jobStore.misfireThreshold","1");
  result.put("org.quartz.plugin.shutdownhook.class",JobShutdownHookPlugin.class.getName());
  result.put("org.quartz.plugin.shutdownhook.cleanShutdown",Boolean.TRUE.toString());
  return result;
}
