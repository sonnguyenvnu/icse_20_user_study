protected void modified(Map<String,Object> properties){
  for (  Entry<String,Object> entry : properties.entrySet()) {
    if (entry.getKey().equals("service.pid") || entry.getKey().equals("component.id") || entry.getKey().equals("component.name")) {
      continue;
    }
    String poolName=entry.getKey();
    Object config=entry.getValue();
    if (config == null) {
      configs.remove(poolName);
    }
    if (config instanceof String) {
      try {
        Integer poolSize=Integer.valueOf((String)config);
        configs.put(poolName,poolSize);
        ThreadPoolExecutor pool=(ThreadPoolExecutor)pools.get(poolName);
        if (pool instanceof ScheduledThreadPoolExecutor) {
          pool.setCorePoolSize(poolSize);
          LOGGER.debug("Updated scheduled thread pool '{}' to size {}",new Object[]{poolName,poolSize});
        }
 else         if (pool instanceof QueueingThreadPoolExecutor) {
          pool.setMaximumPoolSize(poolSize);
          LOGGER.debug("Updated queuing thread pool '{}' to size {}",new Object[]{poolName,poolSize});
        }
      }
 catch (      NumberFormatException e) {
        LOGGER.warn("Ignoring invalid configuration for pool '{}': {} - value must be an integer",new Object[]{poolName,config});
        continue;
      }
    }
  }
}
