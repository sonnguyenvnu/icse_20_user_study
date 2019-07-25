/** 
 * Start the node. If the node is already started, this method is no-op.
 */
public Node start() throws NodeValidationException {
  Logger logger=Loggers.getLogger(Node.class,NODE_NAME_SETTING.get(settings));
  logger.info("starting ...");
  pluginLifecycleComponents.forEach(LifecycleComponent::start);
  injector.getInstance(MappingUpdatedAction.class).setClient(client);
  injector.getInstance(SearchService.class).start();
  nodeService.getMonitorService().start();
  injector.getInstance(ResourceWatcherService.class).start();
  assert localNodeFactory.getNode() != null;
  assert transportService.getLocalNode().equals(localNodeFactory.getNode()) : "transportService has a different local node than the factory provided";
  final MetaData onDiskMetadata;
  try {
    if (DiscoveryNode.isMasterNode(settings) || DiscoveryNode.isDataNode(settings)) {
      onDiskMetadata=injector.getInstance(GatewayMetaState.class).loadMetaState();
    }
 else {
      onDiskMetadata=MetaData.EMPTY_META_DATA;
    }
    assert onDiskMetadata != null : "metadata is null but shouldn't";
  }
 catch (  IOException e) {
    throw new UncheckedIOException(e);
  }
  validateNodeBeforeAcceptingRequests(new BootstrapContext(settings,onDiskMetadata),transportService.boundAddress(),pluginsService.filterPlugins(Plugin.class).stream().flatMap(p -> p.getBootstrapChecks().stream()).collect(Collectors.toList()));
  clusterService.addStateApplier(transportService.getTaskManager());
  discovery.start();
  assert clusterService.localNode().equals(localNodeFactory.getNode()) : "clusterService has a different local node than the factory provided";
  discovery.startInitialJoin();
  clusterService().createOrUpdateElasticAdminKeyspace();
  gatewayService().enableMetaDataPersictency();
  transportService.acceptIncomingRequests();
  if (NetworkModule.HTTP_ENABLED.get(settings)) {
    injector.getInstance(HttpServerTransport.class).start();
  }
  if (WRITE_PORTS_FILE_SETTING.get(settings)) {
    if (NetworkModule.HTTP_ENABLED.get(settings)) {
      HttpServerTransport http=injector.getInstance(HttpServerTransport.class);
      writePortsFile("http",http.boundAddress());
    }
    TransportService transport=injector.getInstance(TransportService.class);
    writePortsFile("transport",transport.boundAddress());
  }
  logger.info("Elasticsearch started state={}",clusterService.state().toString());
  System.out.println("Elassandra started");
  logger.info("started");
  pluginsService.filterPlugins(ClusterPlugin.class).forEach(ClusterPlugin::onNodeStarted);
  return this;
}
