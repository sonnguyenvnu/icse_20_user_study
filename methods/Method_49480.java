@Override public JobClasspathConfigurer newDistCacheConfigurer(){
  return new DistCacheConfigurer("janusgraph-hadoop-core-" + JanusGraphConstants.VERSION + ".jar");
}
