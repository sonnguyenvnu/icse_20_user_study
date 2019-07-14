/** 
 * Creates a scheduler using the specified thread pool, job store, and plugins, and binds it to RMI.
 * @param schedulerName The name for the scheduler.
 * @param schedulerInstanceId The instance ID for the scheduler.
 * @param threadPool The thread pool for executing jobs
 * @param threadExecutor The thread executor for executing jobs
 * @param jobStore The type of job store
 * @param schedulerPluginMap Map from a <code>String</code> plugin names to <code> {@link org.quartz.spi.SchedulerPlugin}</code>s.  Can use "null" if no plugins are required.
 * @param rmiRegistryHost The hostname to register this scheduler with for RMI. Can use "null" if no RMI is required.
 * @param rmiRegistryPort The port for RMI. Typically 1099.
 * @param idleWaitTime The idle wait time in milliseconds. You can specify "-1" for the default value, which is currently 30000 ms.
 * @param maxBatchSize The maximum batch size of triggers, when acquiring them
 * @param batchTimeWindow The time window for which it is allowed to "pre-acquire" triggers to fire
 * @throws SchedulerException if initialization failed
 */
public void createScheduler(String schedulerName,String schedulerInstanceId,ThreadPool threadPool,ThreadExecutor threadExecutor,JobStore jobStore,Map<String,SchedulerPlugin> schedulerPluginMap,String rmiRegistryHost,int rmiRegistryPort,long idleWaitTime,long dbFailureRetryInterval,boolean jmxExport,String jmxObjectName,int maxBatchSize,long batchTimeWindow) throws SchedulerException {
  JobRunShellFactory jrsf=new StdJobRunShellFactory();
  threadPool.setInstanceName(schedulerName);
  threadPool.initialize();
  QuartzSchedulerResources qrs=new QuartzSchedulerResources();
  qrs.setName(schedulerName);
  qrs.setInstanceId(schedulerInstanceId);
  SchedulerDetailsSetter.setDetails(threadPool,schedulerName,schedulerInstanceId);
  qrs.setJobRunShellFactory(jrsf);
  qrs.setThreadPool(threadPool);
  qrs.setThreadExecutor(threadExecutor);
  qrs.setJobStore(jobStore);
  qrs.setMaxBatchSize(maxBatchSize);
  qrs.setBatchTimeWindow(batchTimeWindow);
  qrs.setRMIRegistryHost(rmiRegistryHost);
  qrs.setRMIRegistryPort(rmiRegistryPort);
  qrs.setJMXExport(jmxExport);
  if (jmxObjectName != null) {
    qrs.setJMXObjectName(jmxObjectName);
  }
  if (schedulerPluginMap != null) {
    for (Iterator<SchedulerPlugin> pluginIter=schedulerPluginMap.values().iterator(); pluginIter.hasNext(); ) {
      qrs.addSchedulerPlugin(pluginIter.next());
    }
  }
  QuartzScheduler qs=new QuartzScheduler(qrs,idleWaitTime,dbFailureRetryInterval);
  ClassLoadHelper cch=new CascadingClassLoadHelper();
  cch.initialize();
  SchedulerDetailsSetter.setDetails(jobStore,schedulerName,schedulerInstanceId);
  jobStore.initialize(cch,qs.getSchedulerSignaler());
  Scheduler scheduler=new StdScheduler(qs);
  jrsf.initialize(scheduler);
  qs.initialize();
  if (schedulerPluginMap != null) {
    for (Iterator<Entry<String,SchedulerPlugin>> pluginEntryIter=schedulerPluginMap.entrySet().iterator(); pluginEntryIter.hasNext(); ) {
      Entry<String,SchedulerPlugin> pluginEntry=pluginEntryIter.next();
      pluginEntry.getValue().initialize(pluginEntry.getKey(),scheduler,cch);
    }
  }
  getLog().info("Quartz scheduler '" + scheduler.getSchedulerName());
  getLog().info("Quartz scheduler version: " + qs.getVersion());
  SchedulerRepository schedRep=SchedulerRepository.getInstance();
  qs.addNoGCObject(schedRep);
  schedRep.bind(scheduler);
  initialized=true;
}
