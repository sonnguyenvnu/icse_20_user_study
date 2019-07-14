public static synchronized void start(String config){
  if (started) {
    if (null != config && !config.equals(activeConfig)) {
      log.warn("Can't start in-process Cassandra instance " + "with yaml path {} because an instance was " + "previously started with yaml path {}",config,activeConfig);
    }
    return;
  }
  started=true;
  log.debug("Current working directory: {}",System.getProperty("user.dir"));
  System.setProperty("cassandra.config",config);
  System.setProperty("cassandra-foreground","yes");
  System.setProperty("log4j.defaultInitOverride","false");
  log.info("Starting cassandra with {}",config);
  CassandraDaemon.main(new String[0]);
  activeConfig=config;
}
