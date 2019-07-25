public void init(BrokerStore brokerStore){
  this.brokerStore=brokerStore;
  this.httpPortMapConfig=DynamicConfigLoader.load("broker-http-port-map.properties",false);
  refresh();
  scheduledExecutorService=Executors.newSingleThreadScheduledExecutor(new ThreadFactoryBuilder().setNameFormat("broker-meta-refresh-%d").build());
  scheduledExecutorService.scheduleAtFixedRate(this::refresh,DEFAULT_REFRESH_PERIOD_SECONDS,DEFAULT_REFRESH_PERIOD_SECONDS,TimeUnit.SECONDS);
}
