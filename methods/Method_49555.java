public static ScanMetrics runScanJob(ScanJob scanJob,Configuration conf,String confRootField,org.apache.hadoop.conf.Configuration hadoopConf,Class<? extends InputFormat> inputFormat) throws IOException, InterruptedException, ClassNotFoundException {
  ModifiableHadoopConfiguration scanConf=ModifiableHadoopConfiguration.of(JanusGraphHadoopConfiguration.MAPRED_NS,hadoopConf);
  tryToLoadClassByName(scanJob);
  scanConf.set(JanusGraphHadoopConfiguration.SCAN_JOB_CLASS,scanJob.getClass().getName());
  String jobName=HadoopScanMapper.class.getSimpleName() + "[" + scanJob + "]";
  return runJob(conf,confRootField,hadoopConf,inputFormat,jobName,HadoopScanMapper.class);
}
