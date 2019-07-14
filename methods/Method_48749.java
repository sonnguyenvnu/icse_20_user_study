private String getSuffix(Configuration config){
  final String suffix;
  if (config.has(GraphDatabaseConfiguration.UNIQUE_INSTANCE_ID_SUFFIX)) {
    suffix=LongEncoding.encode(config.get(GraphDatabaseConfiguration.UNIQUE_INSTANCE_ID_SUFFIX));
  }
 else   if (!config.has(GraphDatabaseConfiguration.UNIQUE_INSTANCE_ID_HOSTNAME)) {
    suffix=ManagementFactory.getRuntimeMXBean().getName() + LongEncoding.encode(instanceCounter.incrementAndGet());
  }
 else {
    suffix="";
  }
  return suffix;
}
