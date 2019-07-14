private Scheduler instantiate() throws SchedulerException {
  if (cfg == null) {
    initialize();
  }
  if (initException != null) {
    throw initException;
  }
  JobStore js=null;
  ThreadPool tp=null;
  QuartzScheduler qs=null;
  DBConnectionManager dbMgr=null;
  String instanceIdGeneratorClass=null;
  Properties tProps=null;
  String userTXLocation=null;
  boolean wrapJobInTx=false;
  boolean autoId=false;
  long idleWaitTime=-1;
  long dbFailureRetry=15000L;
  String classLoadHelperClass;
  String jobFactoryClass;
  ThreadExecutor threadExecutor;
  SchedulerRepository schedRep=SchedulerRepository.getInstance();
  String schedName=cfg.getStringProperty(PROP_SCHED_INSTANCE_NAME,"QuartzScheduler");
  String threadName=cfg.getStringProperty(PROP_SCHED_THREAD_NAME,schedName + "_QuartzSchedulerThread");
  String schedInstId=cfg.getStringProperty(PROP_SCHED_INSTANCE_ID,DEFAULT_INSTANCE_ID);
  if (schedInstId.equals(AUTO_GENERATE_INSTANCE_ID)) {
    autoId=true;
    instanceIdGeneratorClass=cfg.getStringProperty(PROP_SCHED_INSTANCE_ID_GENERATOR_CLASS,"org.quartz.simpl.SimpleInstanceIdGenerator");
  }
 else   if (schedInstId.equals(SYSTEM_PROPERTY_AS_INSTANCE_ID)) {
    autoId=true;
    instanceIdGeneratorClass="org.quartz.simpl.SystemPropertyInstanceIdGenerator";
  }
  userTXLocation=cfg.getStringProperty(PROP_SCHED_USER_TX_URL,userTXLocation);
  if (userTXLocation != null && userTXLocation.trim().length() == 0) {
    userTXLocation=null;
  }
  classLoadHelperClass=cfg.getStringProperty(PROP_SCHED_CLASS_LOAD_HELPER_CLASS,"org.quartz.simpl.CascadingClassLoadHelper");
  wrapJobInTx=cfg.getBooleanProperty(PROP_SCHED_WRAP_JOB_IN_USER_TX,wrapJobInTx);
  jobFactoryClass=cfg.getStringProperty(PROP_SCHED_JOB_FACTORY_CLASS,null);
  idleWaitTime=cfg.getLongProperty(PROP_SCHED_IDLE_WAIT_TIME,idleWaitTime);
  if (idleWaitTime > -1 && idleWaitTime < 1000) {
    throw new SchedulerException("org.quartz.scheduler.idleWaitTime of less than 1000ms is not legal.");
  }
  dbFailureRetry=cfg.getLongProperty(PROP_SCHED_DB_FAILURE_RETRY_INTERVAL,dbFailureRetry);
  if (dbFailureRetry < 0) {
    throw new SchedulerException(PROP_SCHED_DB_FAILURE_RETRY_INTERVAL + " of less than 0 ms is not legal.");
  }
  boolean makeSchedulerThreadDaemon=cfg.getBooleanProperty(PROP_SCHED_MAKE_SCHEDULER_THREAD_DAEMON);
  boolean threadsInheritInitalizersClassLoader=cfg.getBooleanProperty(PROP_SCHED_SCHEDULER_THREADS_INHERIT_CONTEXT_CLASS_LOADER_OF_INITIALIZING_THREAD);
  long batchTimeWindow=cfg.getLongProperty(PROP_SCHED_BATCH_TIME_WINDOW,0L);
  int maxBatchSize=cfg.getIntProperty(PROP_SCHED_MAX_BATCH_SIZE,1);
  boolean interruptJobsOnShutdown=cfg.getBooleanProperty(PROP_SCHED_INTERRUPT_JOBS_ON_SHUTDOWN,false);
  boolean interruptJobsOnShutdownWithWait=cfg.getBooleanProperty(PROP_SCHED_INTERRUPT_JOBS_ON_SHUTDOWN_WITH_WAIT,false);
  boolean jmxExport=cfg.getBooleanProperty(PROP_SCHED_JMX_EXPORT);
  String jmxObjectName=cfg.getStringProperty(PROP_SCHED_JMX_OBJECT_NAME);
  boolean jmxProxy=cfg.getBooleanProperty(PROP_SCHED_JMX_PROXY);
  String jmxProxyClass=cfg.getStringProperty(PROP_SCHED_JMX_PROXY_CLASS);
  boolean rmiExport=cfg.getBooleanProperty(PROP_SCHED_RMI_EXPORT,false);
  boolean rmiProxy=cfg.getBooleanProperty(PROP_SCHED_RMI_PROXY,false);
  String rmiHost=cfg.getStringProperty(PROP_SCHED_RMI_HOST,"localhost");
  int rmiPort=cfg.getIntProperty(PROP_SCHED_RMI_PORT,1099);
  int rmiServerPort=cfg.getIntProperty(PROP_SCHED_RMI_SERVER_PORT,-1);
  String rmiCreateRegistry=cfg.getStringProperty(PROP_SCHED_RMI_CREATE_REGISTRY,QuartzSchedulerResources.CREATE_REGISTRY_NEVER);
  String rmiBindName=cfg.getStringProperty(PROP_SCHED_RMI_BIND_NAME);
  if (jmxProxy && rmiProxy) {
    throw new SchedulerConfigException("Cannot proxy both RMI and JMX.");
  }
  boolean managementRESTServiceEnabled=cfg.getBooleanProperty(MANAGEMENT_REST_SERVICE_ENABLED,false);
  String managementRESTServiceHostAndPort=cfg.getStringProperty(MANAGEMENT_REST_SERVICE_HOST_PORT,"0.0.0.0:9889");
  Properties schedCtxtProps=cfg.getPropertyGroup(PROP_SCHED_CONTEXT_PREFIX,true);
  if (rmiProxy) {
    if (autoId) {
      schedInstId=DEFAULT_INSTANCE_ID;
    }
    String uid=(rmiBindName == null) ? QuartzSchedulerResources.getUniqueIdentifier(schedName,schedInstId) : rmiBindName;
    RemoteScheduler remoteScheduler=new RemoteScheduler(uid,rmiHost,rmiPort);
    schedRep.bind(remoteScheduler);
    return remoteScheduler;
  }
  ClassLoadHelper loadHelper=null;
  try {
    loadHelper=(ClassLoadHelper)loadClass(classLoadHelperClass).newInstance();
  }
 catch (  Exception e) {
    throw new SchedulerConfigException("Unable to instantiate class load helper class: " + e.getMessage(),e);
  }
  loadHelper.initialize();
  if (jmxProxy) {
    if (autoId) {
      schedInstId=DEFAULT_INSTANCE_ID;
    }
    if (jmxProxyClass == null) {
      throw new SchedulerConfigException("No JMX Proxy Scheduler class provided");
    }
    RemoteMBeanScheduler jmxScheduler=null;
    try {
      jmxScheduler=(RemoteMBeanScheduler)loadHelper.loadClass(jmxProxyClass).newInstance();
    }
 catch (    Exception e) {
      throw new SchedulerConfigException("Unable to instantiate RemoteMBeanScheduler class.",e);
    }
    if (jmxObjectName == null) {
      jmxObjectName=QuartzSchedulerResources.generateJMXObjectName(schedName,schedInstId);
    }
    jmxScheduler.setSchedulerObjectName(jmxObjectName);
    tProps=cfg.getPropertyGroup(PROP_SCHED_JMX_PROXY,true);
    try {
      setBeanProps(jmxScheduler,tProps);
    }
 catch (    Exception e) {
      initException=new SchedulerException("RemoteMBeanScheduler class '" + jmxProxyClass + "' props could not be configured.",e);
      throw initException;
    }
    jmxScheduler.initialize();
    schedRep.bind(jmxScheduler);
    return jmxScheduler;
  }
  JobFactory jobFactory=null;
  if (jobFactoryClass != null) {
    try {
      jobFactory=(JobFactory)loadHelper.loadClass(jobFactoryClass).newInstance();
    }
 catch (    Exception e) {
      throw new SchedulerConfigException("Unable to instantiate JobFactory class: " + e.getMessage(),e);
    }
    tProps=cfg.getPropertyGroup(PROP_SCHED_JOB_FACTORY_PREFIX,true);
    try {
      setBeanProps(jobFactory,tProps);
    }
 catch (    Exception e) {
      initException=new SchedulerException("JobFactory class '" + jobFactoryClass + "' props could not be configured.",e);
      throw initException;
    }
  }
  InstanceIdGenerator instanceIdGenerator=null;
  if (instanceIdGeneratorClass != null) {
    try {
      instanceIdGenerator=(InstanceIdGenerator)loadHelper.loadClass(instanceIdGeneratorClass).newInstance();
    }
 catch (    Exception e) {
      throw new SchedulerConfigException("Unable to instantiate InstanceIdGenerator class: " + e.getMessage(),e);
    }
    tProps=cfg.getPropertyGroup(PROP_SCHED_INSTANCE_ID_GENERATOR_PREFIX,true);
    try {
      setBeanProps(instanceIdGenerator,tProps);
    }
 catch (    Exception e) {
      initException=new SchedulerException("InstanceIdGenerator class '" + instanceIdGeneratorClass + "' props could not be configured.",e);
      throw initException;
    }
  }
  String tpClass=cfg.getStringProperty(PROP_THREAD_POOL_CLASS,SimpleThreadPool.class.getName());
  if (tpClass == null) {
    initException=new SchedulerException("ThreadPool class not specified. ");
    throw initException;
  }
  try {
    tp=(ThreadPool)loadHelper.loadClass(tpClass).newInstance();
  }
 catch (  Exception e) {
    initException=new SchedulerException("ThreadPool class '" + tpClass + "' could not be instantiated.",e);
    throw initException;
  }
  tProps=cfg.getPropertyGroup(PROP_THREAD_POOL_PREFIX,true);
  try {
    setBeanProps(tp,tProps);
  }
 catch (  Exception e) {
    initException=new SchedulerException("ThreadPool class '" + tpClass + "' props could not be configured.",e);
    throw initException;
  }
  String jsClass=cfg.getStringProperty(PROP_JOB_STORE_CLASS,RAMJobStore.class.getName());
  if (jsClass == null) {
    initException=new SchedulerException("JobStore class not specified. ");
    throw initException;
  }
  try {
    js=(JobStore)loadHelper.loadClass(jsClass).newInstance();
  }
 catch (  Exception e) {
    initException=new SchedulerException("JobStore class '" + jsClass + "' could not be instantiated.",e);
    throw initException;
  }
  SchedulerDetailsSetter.setDetails(js,schedName,schedInstId);
  tProps=cfg.getPropertyGroup(PROP_JOB_STORE_PREFIX,true,new String[]{PROP_JOB_STORE_LOCK_HANDLER_PREFIX});
  try {
    setBeanProps(js,tProps);
  }
 catch (  Exception e) {
    initException=new SchedulerException("JobStore class '" + jsClass + "' props could not be configured.",e);
    throw initException;
  }
  if (js instanceof JobStoreSupport) {
    String lockHandlerClass=cfg.getStringProperty(PROP_JOB_STORE_LOCK_HANDLER_CLASS);
    if (lockHandlerClass != null) {
      try {
        Semaphore lockHandler=(Semaphore)loadHelper.loadClass(lockHandlerClass).newInstance();
        tProps=cfg.getPropertyGroup(PROP_JOB_STORE_LOCK_HANDLER_PREFIX,true);
        if (lockHandler instanceof TablePrefixAware) {
          tProps.setProperty(PROP_TABLE_PREFIX,((JobStoreSupport)js).getTablePrefix());
          tProps.setProperty(PROP_SCHED_NAME,schedName);
        }
        try {
          setBeanProps(lockHandler,tProps);
        }
 catch (        Exception e) {
          initException=new SchedulerException("JobStore LockHandler class '" + lockHandlerClass + "' props could not be configured.",e);
          throw initException;
        }
        ((JobStoreSupport)js).setLockHandler(lockHandler);
        getLog().info("Using custom data access locking (synchronization): " + lockHandlerClass);
      }
 catch (      Exception e) {
        initException=new SchedulerException("JobStore LockHandler class '" + lockHandlerClass + "' could not be instantiated.",e);
        throw initException;
      }
    }
  }
  String[] dsNames=cfg.getPropertyGroups(PROP_DATASOURCE_PREFIX);
  for (int i=0; i < dsNames.length; i++) {
    PropertiesParser pp=new PropertiesParser(cfg.getPropertyGroup(PROP_DATASOURCE_PREFIX + "." + dsNames[i],true));
    String cpClass=pp.getStringProperty(PROP_CONNECTION_PROVIDER_CLASS,null);
    if (cpClass != null) {
      ConnectionProvider cp=null;
      try {
        cp=(ConnectionProvider)loadHelper.loadClass(cpClass).newInstance();
      }
 catch (      Exception e) {
        initException=new SchedulerException("ConnectionProvider class '" + cpClass + "' could not be instantiated.",e);
        throw initException;
      }
      try {
        pp.getUnderlyingProperties().remove(PROP_CONNECTION_PROVIDER_CLASS);
        if (cp instanceof PoolingConnectionProvider) {
          populateProviderWithExtraProps((PoolingConnectionProvider)cp,pp.getUnderlyingProperties());
        }
 else {
          setBeanProps(cp,pp.getUnderlyingProperties());
        }
        cp.initialize();
      }
 catch (      Exception e) {
        initException=new SchedulerException("ConnectionProvider class '" + cpClass + "' props could not be configured.",e);
        throw initException;
      }
      dbMgr=DBConnectionManager.getInstance();
      dbMgr.addConnectionProvider(dsNames[i],cp);
    }
 else {
      String dsJndi=pp.getStringProperty(PROP_DATASOURCE_JNDI_URL,null);
      if (dsJndi != null) {
        boolean dsAlwaysLookup=pp.getBooleanProperty(PROP_DATASOURCE_JNDI_ALWAYS_LOOKUP);
        String dsJndiInitial=pp.getStringProperty(PROP_DATASOURCE_JNDI_INITIAL);
        String dsJndiProvider=pp.getStringProperty(PROP_DATASOURCE_JNDI_PROVDER);
        String dsJndiPrincipal=pp.getStringProperty(PROP_DATASOURCE_JNDI_PRINCIPAL);
        String dsJndiCredentials=pp.getStringProperty(PROP_DATASOURCE_JNDI_CREDENTIALS);
        Properties props=null;
        if (null != dsJndiInitial || null != dsJndiProvider || null != dsJndiPrincipal || null != dsJndiCredentials) {
          props=new Properties();
          if (dsJndiInitial != null) {
            props.put(PROP_DATASOURCE_JNDI_INITIAL,dsJndiInitial);
          }
          if (dsJndiProvider != null) {
            props.put(PROP_DATASOURCE_JNDI_PROVDER,dsJndiProvider);
          }
          if (dsJndiPrincipal != null) {
            props.put(PROP_DATASOURCE_JNDI_PRINCIPAL,dsJndiPrincipal);
          }
          if (dsJndiCredentials != null) {
            props.put(PROP_DATASOURCE_JNDI_CREDENTIALS,dsJndiCredentials);
          }
        }
        JNDIConnectionProvider cp=new JNDIConnectionProvider(dsJndi,props,dsAlwaysLookup);
        dbMgr=DBConnectionManager.getInstance();
        dbMgr.addConnectionProvider(dsNames[i],cp);
      }
 else {
        String poolingProvider=pp.getStringProperty(PoolingConnectionProvider.POOLING_PROVIDER);
        String dsDriver=pp.getStringProperty(PoolingConnectionProvider.DB_DRIVER);
        String dsURL=pp.getStringProperty(PoolingConnectionProvider.DB_URL);
        if (dsDriver == null) {
          initException=new SchedulerException("Driver not specified for DataSource: " + dsNames[i]);
          throw initException;
        }
        if (dsURL == null) {
          initException=new SchedulerException("DB URL not specified for DataSource: " + dsNames[i]);
          throw initException;
        }
        if (poolingProvider != null && poolingProvider.equals(PoolingConnectionProvider.POOLING_PROVIDER_HIKARICP)) {
          cpClass="org.quartz.utils.HikariCpPoolingConnectionProvider";
        }
 else {
          cpClass="org.quartz.utils.C3p0PoolingConnectionProvider";
        }
        log.info("Using ConnectionProvider class '" + cpClass + "' for data source '" + dsNames[i] + "'");
        try {
          ConnectionProvider cp=null;
          try {
            Constructor constructor=loadHelper.loadClass(cpClass).getConstructor(Properties.class);
            cp=(ConnectionProvider)constructor.newInstance(pp.getUnderlyingProperties());
          }
 catch (          Exception e) {
            initException=new SchedulerException("ConnectionProvider class '" + cpClass + "' could not be instantiated.",e);
            throw initException;
          }
          dbMgr=DBConnectionManager.getInstance();
          dbMgr.addConnectionProvider(dsNames[i],cp);
          populateProviderWithExtraProps((PoolingConnectionProvider)cp,pp.getUnderlyingProperties());
        }
 catch (        Exception sqle) {
          initException=new SchedulerException("Could not initialize DataSource: " + dsNames[i],sqle);
          throw initException;
        }
      }
    }
  }
  String[] pluginNames=cfg.getPropertyGroups(PROP_PLUGIN_PREFIX);
  SchedulerPlugin[] plugins=new SchedulerPlugin[pluginNames.length];
  for (int i=0; i < pluginNames.length; i++) {
    Properties pp=cfg.getPropertyGroup(PROP_PLUGIN_PREFIX + "." + pluginNames[i],true);
    String plugInClass=pp.getProperty(PROP_PLUGIN_CLASS,null);
    if (plugInClass == null) {
      initException=new SchedulerException("SchedulerPlugin class not specified for plugin '" + pluginNames[i] + "'");
      throw initException;
    }
    SchedulerPlugin plugin=null;
    try {
      plugin=(SchedulerPlugin)loadHelper.loadClass(plugInClass).newInstance();
    }
 catch (    Exception e) {
      initException=new SchedulerException("SchedulerPlugin class '" + plugInClass + "' could not be instantiated.",e);
      throw initException;
    }
    try {
      setBeanProps(plugin,pp);
    }
 catch (    Exception e) {
      initException=new SchedulerException("JobStore SchedulerPlugin '" + plugInClass + "' props could not be configured.",e);
      throw initException;
    }
    plugins[i]=plugin;
  }
  Class<?>[] strArg=new Class[]{String.class};
  String[] jobListenerNames=cfg.getPropertyGroups(PROP_JOB_LISTENER_PREFIX);
  JobListener[] jobListeners=new JobListener[jobListenerNames.length];
  for (int i=0; i < jobListenerNames.length; i++) {
    Properties lp=cfg.getPropertyGroup(PROP_JOB_LISTENER_PREFIX + "." + jobListenerNames[i],true);
    String listenerClass=lp.getProperty(PROP_LISTENER_CLASS,null);
    if (listenerClass == null) {
      initException=new SchedulerException("JobListener class not specified for listener '" + jobListenerNames[i] + "'");
      throw initException;
    }
    JobListener listener=null;
    try {
      listener=(JobListener)loadHelper.loadClass(listenerClass).newInstance();
    }
 catch (    Exception e) {
      initException=new SchedulerException("JobListener class '" + listenerClass + "' could not be instantiated.",e);
      throw initException;
    }
    try {
      Method nameSetter=null;
      try {
        nameSetter=listener.getClass().getMethod("setName",strArg);
      }
 catch (      NoSuchMethodException ignore) {
      }
      if (nameSetter != null) {
        nameSetter.invoke(listener,new Object[]{jobListenerNames[i]});
      }
      setBeanProps(listener,lp);
    }
 catch (    Exception e) {
      initException=new SchedulerException("JobListener '" + listenerClass + "' props could not be configured.",e);
      throw initException;
    }
    jobListeners[i]=listener;
  }
  String[] triggerListenerNames=cfg.getPropertyGroups(PROP_TRIGGER_LISTENER_PREFIX);
  TriggerListener[] triggerListeners=new TriggerListener[triggerListenerNames.length];
  for (int i=0; i < triggerListenerNames.length; i++) {
    Properties lp=cfg.getPropertyGroup(PROP_TRIGGER_LISTENER_PREFIX + "." + triggerListenerNames[i],true);
    String listenerClass=lp.getProperty(PROP_LISTENER_CLASS,null);
    if (listenerClass == null) {
      initException=new SchedulerException("TriggerListener class not specified for listener '" + triggerListenerNames[i] + "'");
      throw initException;
    }
    TriggerListener listener=null;
    try {
      listener=(TriggerListener)loadHelper.loadClass(listenerClass).newInstance();
    }
 catch (    Exception e) {
      initException=new SchedulerException("TriggerListener class '" + listenerClass + "' could not be instantiated.",e);
      throw initException;
    }
    try {
      Method nameSetter=null;
      try {
        nameSetter=listener.getClass().getMethod("setName",strArg);
      }
 catch (      NoSuchMethodException ignore) {
      }
      if (nameSetter != null) {
        nameSetter.invoke(listener,new Object[]{triggerListenerNames[i]});
      }
      setBeanProps(listener,lp);
    }
 catch (    Exception e) {
      initException=new SchedulerException("TriggerListener '" + listenerClass + "' props could not be configured.",e);
      throw initException;
    }
    triggerListeners[i]=listener;
  }
  boolean tpInited=false;
  boolean qsInited=false;
  String threadExecutorClass=cfg.getStringProperty(PROP_THREAD_EXECUTOR_CLASS);
  if (threadExecutorClass != null) {
    tProps=cfg.getPropertyGroup(PROP_THREAD_EXECUTOR,true);
    try {
      threadExecutor=(ThreadExecutor)loadHelper.loadClass(threadExecutorClass).newInstance();
      log.info("Using custom implementation for ThreadExecutor: " + threadExecutorClass);
      setBeanProps(threadExecutor,tProps);
    }
 catch (    Exception e) {
      initException=new SchedulerException("ThreadExecutor class '" + threadExecutorClass + "' could not be instantiated.",e);
      throw initException;
    }
  }
 else {
    log.info("Using default implementation for ThreadExecutor");
    threadExecutor=new DefaultThreadExecutor();
  }
  try {
    JobRunShellFactory jrsf=null;
    if (userTXLocation != null) {
      UserTransactionHelper.setUserTxLocation(userTXLocation);
    }
    if (wrapJobInTx) {
      jrsf=new JTAJobRunShellFactory();
    }
 else {
      jrsf=new JTAAnnotationAwareJobRunShellFactory();
    }
    if (autoId) {
      try {
        schedInstId=DEFAULT_INSTANCE_ID;
        if (js.isClustered()) {
          schedInstId=instanceIdGenerator.generateInstanceId();
        }
      }
 catch (      Exception e) {
        getLog().error("Couldn't generate instance Id!",e);
        throw new IllegalStateException("Cannot run without an instance id.");
      }
    }
    if (js.getClass().getName().startsWith("org.terracotta.quartz")) {
      try {
        String uuid=(String)js.getClass().getMethod("getUUID").invoke(js);
        if (schedInstId.equals(DEFAULT_INSTANCE_ID)) {
          schedInstId="TERRACOTTA_CLUSTERED,node=" + uuid;
          if (jmxObjectName == null) {
            jmxObjectName=QuartzSchedulerResources.generateJMXObjectName(schedName,schedInstId);
          }
        }
 else         if (jmxObjectName == null) {
          jmxObjectName=QuartzSchedulerResources.generateJMXObjectName(schedName,schedInstId + ",node=" + uuid);
        }
      }
 catch (      Exception e) {
        throw new RuntimeException("Problem obtaining node id from TerracottaJobStore.",e);
      }
      if (null == cfg.getStringProperty(PROP_SCHED_JMX_EXPORT)) {
        jmxExport=true;
      }
    }
    if (js instanceof JobStoreSupport) {
      JobStoreSupport jjs=(JobStoreSupport)js;
      jjs.setDbRetryInterval(dbFailureRetry);
      if (threadsInheritInitalizersClassLoader)       jjs.setThreadsInheritInitializersClassLoadContext(threadsInheritInitalizersClassLoader);
      jjs.setThreadExecutor(threadExecutor);
    }
    QuartzSchedulerResources rsrcs=new QuartzSchedulerResources();
    rsrcs.setName(schedName);
    rsrcs.setThreadName(threadName);
    rsrcs.setInstanceId(schedInstId);
    rsrcs.setJobRunShellFactory(jrsf);
    rsrcs.setMakeSchedulerThreadDaemon(makeSchedulerThreadDaemon);
    rsrcs.setThreadsInheritInitializersClassLoadContext(threadsInheritInitalizersClassLoader);
    rsrcs.setBatchTimeWindow(batchTimeWindow);
    rsrcs.setMaxBatchSize(maxBatchSize);
    rsrcs.setInterruptJobsOnShutdown(interruptJobsOnShutdown);
    rsrcs.setInterruptJobsOnShutdownWithWait(interruptJobsOnShutdownWithWait);
    rsrcs.setJMXExport(jmxExport);
    rsrcs.setJMXObjectName(jmxObjectName);
    if (managementRESTServiceEnabled) {
      ManagementRESTServiceConfiguration managementRESTServiceConfiguration=new ManagementRESTServiceConfiguration();
      managementRESTServiceConfiguration.setBind(managementRESTServiceHostAndPort);
      managementRESTServiceConfiguration.setEnabled(managementRESTServiceEnabled);
      rsrcs.setManagementRESTServiceConfiguration(managementRESTServiceConfiguration);
    }
    if (rmiExport) {
      rsrcs.setRMIRegistryHost(rmiHost);
      rsrcs.setRMIRegistryPort(rmiPort);
      rsrcs.setRMIServerPort(rmiServerPort);
      rsrcs.setRMICreateRegistryStrategy(rmiCreateRegistry);
      rsrcs.setRMIBindName(rmiBindName);
    }
    SchedulerDetailsSetter.setDetails(tp,schedName,schedInstId);
    rsrcs.setThreadExecutor(threadExecutor);
    threadExecutor.initialize();
    rsrcs.setThreadPool(tp);
    if (tp instanceof SimpleThreadPool) {
      if (threadsInheritInitalizersClassLoader)       ((SimpleThreadPool)tp).setThreadsInheritContextClassLoaderOfInitializingThread(threadsInheritInitalizersClassLoader);
    }
    tp.initialize();
    tpInited=true;
    rsrcs.setJobStore(js);
    for (int i=0; i < plugins.length; i++) {
      rsrcs.addSchedulerPlugin(plugins[i]);
    }
    qs=new QuartzScheduler(rsrcs,idleWaitTime,dbFailureRetry);
    qsInited=true;
    Scheduler scheduler=instantiate(rsrcs,qs);
    if (jobFactory != null) {
      qs.setJobFactory(jobFactory);
    }
    for (int i=0; i < plugins.length; i++) {
      plugins[i].initialize(pluginNames[i],scheduler,loadHelper);
    }
    for (int i=0; i < jobListeners.length; i++) {
      qs.getListenerManager().addJobListener(jobListeners[i],EverythingMatcher.allJobs());
    }
    for (int i=0; i < triggerListeners.length; i++) {
      qs.getListenerManager().addTriggerListener(triggerListeners[i],EverythingMatcher.allTriggers());
    }
    for (    Object key : schedCtxtProps.keySet()) {
      String val=schedCtxtProps.getProperty((String)key);
      scheduler.getContext().put((String)key,val);
    }
    js.setInstanceId(schedInstId);
    js.setInstanceName(schedName);
    js.setThreadPoolSize(tp.getPoolSize());
    js.initialize(loadHelper,qs.getSchedulerSignaler());
    jrsf.initialize(scheduler);
    qs.initialize();
    getLog().info("Quartz scheduler '" + scheduler.getSchedulerName() + "' initialized from " + propSrc);
    getLog().info("Quartz scheduler version: " + qs.getVersion());
    qs.addNoGCObject(schedRep);
    if (dbMgr != null) {
      qs.addNoGCObject(dbMgr);
    }
    schedRep.bind(scheduler);
    return scheduler;
  }
 catch (  SchedulerException e) {
    shutdownFromInstantiateException(tp,qs,tpInited,qsInited);
    throw e;
  }
catch (  RuntimeException re) {
    shutdownFromInstantiateException(tp,qs,tpInited,qsInited);
    throw re;
  }
catch (  Error re) {
    shutdownFromInstantiateException(tp,qs,tpInited,qsInited);
    throw re;
  }
}
