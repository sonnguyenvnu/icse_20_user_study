private void start(String props,InetAddress bind_addr,int port,int min_threads,int max_threads,long rpc_timeout,long caching_time,boolean migrate_data,boolean use_l1_cache,int l1_max_entries,long l1_reaping_interval,int l2_max_entries,long l2_reaping_interval) throws Exception {
  MBeanServer server=ManagementFactory.getPlatformMBeanServer();
  connector=new MemcachedConnector(bind_addr,port,null);
  connector.setThreadPoolCoreThreads(min_threads);
  connector.setThreadPoolMaxThreads(max_threads);
  JmxConfigurator.register(connector,server,BASENAME + ":name=connector");
  cache=new PartitionedHashMap(props,"memcached-cluster");
  cache.setCallTimeout(rpc_timeout);
  cache.setCachingTime(caching_time);
  cache.setMigrateData(migrate_data);
  JmxConfigurator.register(cache,server,BASENAME + ":name=cache");
  JmxConfigurator.register(cache.getL2Cache(),server,BASENAME + ":name=l2-cache");
  if (use_l1_cache) {
    Cache<String,byte[]> l1_cache=new Cache<>();
    cache.setL1Cache(l1_cache);
    if (l1_reaping_interval > 0)     l1_cache.enableReaping(l1_reaping_interval);
    if (l1_max_entries > 0)     l1_cache.setMaxNumberOfEntries(l1_max_entries);
    JmxConfigurator.register(cache.getL1Cache(),server,BASENAME + ":name=l1-cache");
  }
  if (l2_max_entries > 0 || l2_reaping_interval > 0) {
    Cache<String,byte[]> l2_cache=cache.getL2Cache();
    if (l2_max_entries > 0)     l2_cache.setMaxNumberOfEntries(l2_max_entries);
    if (l2_reaping_interval > 0)     l2_cache.enableReaping(l2_reaping_interval);
  }
  connector.setCache(cache);
  Runtime.getRuntime().addShutdownHook(new Thread(){
    public void run(){
      cache.stop();
      try {
        connector.stop();
      }
 catch (      Exception e) {
      }
    }
  }
);
  cache.start();
  connector.start();
}
