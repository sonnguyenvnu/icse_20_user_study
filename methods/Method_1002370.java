/** 
 * @return {@link D2Client} that is not started yet. Call start(Callback) to start it.
 */
public D2Client build(){
  final Map<String,TransportClientFactory> transportClientFactories=(_config.clientFactories == null) ? createDefaultTransportClientFactories() : _config.clientFactories;
  List<ScheduledExecutorService> executorsToShutDown=new ArrayList<>();
  if (_config.startUpExecutorService == null) {
    _config.startUpExecutorService=Executors.newScheduledThreadPool(0,new NamedThreadFactory("D2 StartupOnlyExecutor"));
    executorsToShutDown.add(_config.startUpExecutorService);
  }
  if (_config._executorService == null) {
    LOG.warn("No executor service passed as argument. Pass it for " + "enhanced monitoring and to have better control over the executor.");
    _config._executorService=Executors.newSingleThreadScheduledExecutor(new NamedThreadFactory("D2 PropertyEventExecutor"));
    executorsToShutDown.add(_config._executorService);
  }
  if (_config.downstreamServicesFetcher == null) {
    _config.downstreamServicesFetcher=new FSBasedDownstreamServicesFetcher(_config.fsBasePath,_config.d2ServicePath);
  }
  final Map<String,LoadBalancerStrategyFactory<? extends LoadBalancerStrategy>> loadBalancerStrategyFactories=createDefaultLoadBalancerStrategyFactories();
  final D2ClientConfig cfg=new D2ClientConfig(_config.zkHosts,_config.zkSessionTimeoutInMs,_config.zkStartupTimeoutInMs,_config.lbWaitTimeout,_config.lbWaitUnit,_config.flagFile,_config.basePath,_config.fsBasePath,_config.componentFactory,transportClientFactories,_config.lbWithFacilitiesFactory,_config.sslContext,_config.sslParameters,_config.isSSLEnabled,_config.shutdownAsynchronously,_config.isSymlinkAware,_config.clientServicesConfig,_config.d2ServicePath,_config.useNewEphemeralStoreWatcher,_config.healthCheckOperations,_config._executorService,_config.retry,_config.retryLimit,_config.warmUp,_config.warmUpTimeoutSeconds,_config.warmUpConcurrentRequests,_config.downstreamServicesFetcher,_config.backupRequestsEnabled,_config.backupRequestsStrategyStatsConsumer,_config.backupRequestsLatencyNotificationInterval,_config.backupRequestsLatencyNotificationIntervalUnit,_config._backupRequestsExecutorService,_config.eventEmitter,_config.partitionAccessorRegistry,_config.zooKeeperDecorator,_config.enableSaveUriDataOnDisk,loadBalancerStrategyFactories,_config.requestTimeoutHandlerEnabled,_config.sslSessionValidatorFactory,_config.zkConnectionToUseForLB,_config.startUpExecutorService);
  final LoadBalancerWithFacilitiesFactory loadBalancerFactory=(_config.lbWithFacilitiesFactory == null) ? new ZKFSLoadBalancerWithFacilitiesFactory() : _config.lbWithFacilitiesFactory;
  LoadBalancerWithFacilities loadBalancer=loadBalancerFactory.create(cfg);
  D2Client d2Client=new DynamicClient(loadBalancer,loadBalancer,_restOverStream);
  if (_config.requestTimeoutHandlerEnabled) {
    d2Client=new RequestTimeoutClient(d2Client,loadBalancer,_config._executorService);
  }
  if (_config.backupRequestsEnabled) {
    ScheduledExecutorService executor=_config._backupRequestsExecutorService;
    if (executor == null) {
      LOG.warn("Backup Requests Executor not configured, creating one with core pool size equal to: " + Runtime.getRuntime().availableProcessors());
      executor=Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors(),new NamedThreadFactory("Backup Requests Executor"));
      executorsToShutDown.add(executor);
    }
    d2Client=new BackupRequestsClient(d2Client,loadBalancer,executor,_config.backupRequestsStrategyStatsConsumer,_config.backupRequestsLatencyNotificationInterval,_config.backupRequestsLatencyNotificationIntervalUnit);
  }
  if (_config.retry) {
    d2Client=new RetryClient(d2Client,_config.retryLimit);
  }
  if (_config.clientFactories != transportClientFactories) {
    d2Client=new TransportClientFactoryAwareD2Client(d2Client,transportClientFactories.values());
  }
  if (executorsToShutDown.size() > 0) {
    d2Client=new ExecutorShutdownAwareD2Client(d2Client,executorsToShutDown);
  }
  return d2Client;
}
