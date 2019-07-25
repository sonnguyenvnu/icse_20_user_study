public void activate(boolean addShutdownHook,boolean createNode,Settings settings,Environment env,Collection<Class<? extends Plugin>> pluginList){
  try {
    DatabaseDescriptor.daemonInitialization();
    DatabaseDescriptor.createAllDirectories();
  }
 catch (  ExceptionInInitializerError e) {
    System.out.println("Exception (" + e.getClass().getName() + ") encountered during startup: " + e.getMessage());
    String errorMessage=buildErrorMessage("Initialization",e);
    System.err.println(errorMessage);
    System.err.flush();
    System.exit(3);
  }
  String pidFile=System.getProperty("cassandra-pidfile");
  if (pidFile != null) {
    new File(pidFile).deleteOnExit();
  }
  NativeLibrary.tryMlockall();
  org.elasticsearch.bootstrap.Bootstrap.initializeNatives(env.tmpFile(),settings.getAsBoolean("bootstrap.memory_lock",true),settings.getAsBoolean("bootstrap.system_call_filter",false),settings.getAsBoolean("bootstrap.ctrlhandler",true));
  org.elasticsearch.bootstrap.Bootstrap.initializeProbes();
  if (addShutdownHook) {
    Runtime.getRuntime().addShutdownHook(new Thread(){
      @Override public void run(){
        if (node != null) {
          try {
            node.close();
          }
 catch (          IOException e) {
            throw new RuntimeException(e);
          }
        }
      }
    }
);
  }
  if (createNode) {
    List<Class<? extends Plugin>> pluginList2=Lists.newArrayList(pluginList);
    pluginList2.add(ElassandraPlugin.class);
    this.node=new Node(getSettings(),pluginList2);
  }
  ElasticSecondaryIndex.runsElassandra=true;
  try {
    MBeanServer mbs=ManagementFactory.getPlatformMBeanServer();
    mbs.registerMBean(new StandardMBean(new NativeAccess(),NativeAccessMBean.class),new ObjectName(MBEAN_NAME));
  }
 catch (  Exception e) {
    logger.error("error registering MBean {}",MBEAN_NAME,e);
  }
  if (FBUtilities.isWindows) {
    WindowsTimer.startTimerPeriod(DatabaseDescriptor.getWindowsTimerInterval());
  }
  super.setup();
  super.start();
  if (this.node != null) {
    try {
      this.node.clusterService().submitNumberOfShardsAndReplicasUpdate("user-keyspaces-bootstraped");
      this.node.start();
    }
 catch (    NodeValidationException e) {
      throw new RuntimeException(e);
    }
  }
}
