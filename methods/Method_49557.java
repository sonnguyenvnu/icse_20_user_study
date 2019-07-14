public ScanMetrics run() throws InterruptedException, IOException, ClassNotFoundException {
  org.apache.hadoop.conf.Configuration hadoopConf=null != baseHadoopConf ? baseHadoopConf : new org.apache.hadoop.conf.Configuration();
  if (null != janusgraphConf) {
    String prefix=ConfigElement.getPath(JanusGraphHadoopConfiguration.GRAPH_CONFIG_KEYS,true) + ".";
    for (    String k : janusgraphConf.getKeys("")) {
      hadoopConf.set(prefix + k,janusgraphConf.get(k,Object.class).toString());
      log.debug("Set: {}={}",prefix + k,janusgraphConf.get(k,Object.class).toString());
    }
  }
  Preconditions.checkNotNull(hadoopConf);
  if (null != scanJob) {
    return HadoopScanRunner.runScanJob(scanJob,scanJobConf,scanJobConfRoot,hadoopConf,HBaseBinaryInputFormat.class);
  }
 else {
    return HadoopScanRunner.runVertexScanJob(vertexScanJob,scanJobConf,scanJobConfRoot,hadoopConf,HBaseBinaryInputFormat.class);
  }
}
