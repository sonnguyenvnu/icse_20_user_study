public static ScanMetrics runVertexScanJob(VertexScanJob vertexScanJob,Configuration conf,String confRootField,org.apache.hadoop.conf.Configuration hadoopConf,Class<? extends InputFormat> inputFormat) throws IOException, InterruptedException, ClassNotFoundException {
  ModifiableHadoopConfiguration scanConf=ModifiableHadoopConfiguration.of(JanusGraphHadoopConfiguration.MAPRED_NS,hadoopConf);
  tryToLoadClassByName(vertexScanJob);
  scanConf.set(JanusGraphHadoopConfiguration.SCAN_JOB_CLASS,vertexScanJob.getClass().getName());
  String jobName=HadoopScanMapper.class.getSimpleName() + "[" + vertexScanJob + "]";
  return runJob(conf,confRootField,hadoopConf,inputFormat,jobName,HadoopVertexScanMapper.class);
}
