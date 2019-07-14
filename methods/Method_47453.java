/** 
 * ????
 * @return
 * @throws IOException
 */
private Properties quartzProperties() throws IOException {
  Properties prop=new Properties();
  prop.put("quartz.scheduler.instanceName",quartzInstanceName);
  prop.put("org.quartz.scheduler.instanceId","AUTO");
  prop.put("org.quartz.scheduler.skipUpdateCheck","true");
  prop.put("org.quartz.scheduler.jmx.export","true");
  prop.put("org.quartz.jobStore.driverDelegateClass","org.quartz.impl.jdbcjobstore.StdJDBCDelegate");
  prop.put("org.quartz.jobStore.class","org.quartz.impl.jdbcjobstore.JobStoreTX");
  prop.put("org.quartz.jobStore.dataSource","quartzDataSource");
  prop.put("org.quartz.jobStore.tablePrefix","QRTZ_");
  prop.put("org.quartz.jobStore.isClustered","true");
  prop.put("org.quartz.jobStore.clusterCheckinInterval","20000");
  prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime","1");
  prop.put("org.quartz.jobStore.misfireThreshold","120000");
  prop.put("org.quartz.jobStore.txIsolationLevelSerializable","true");
  prop.put("org.quartz.jobStore.selectWithLockSQL","SELECT * FROM {0}LOCKS WHERE LOCK_NAME = ? FOR UPDATE");
  prop.put("org.quartz.threadPool.class","org.quartz.simpl.SimpleThreadPool");
  prop.put("org.quartz.threadPool.threadCount","10");
  prop.put("org.quartz.threadPool.threadPriority","5");
  prop.put("org.quartz.threadPool.threadsInheritContextClassLoaderOfInitializingThread","true");
  prop.put("org.quartz.plugin.triggHistory.class","org.quartz.plugins.history.LoggingJobHistoryPlugin");
  prop.put("org.quartz.plugin.shutdownhook.class","org.quartz.plugins.management.ShutdownHookPlugin");
  prop.put("org.quartz.plugin.shutdownhook.cleanShutdown","true");
  return prop;
}
