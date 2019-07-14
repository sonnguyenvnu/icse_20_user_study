public static ScanMetrics hbaseRemove(Properties janusgraphProperties,String indexName,String relationType,Configuration hadoopBaseConf) throws InterruptedException, IOException, ClassNotFoundException {
  IndexRemoveJob job=new IndexRemoveJob();
  HBaseHadoopScanRunner cr=new HBaseHadoopScanRunner(job);
  ModifiableConfiguration mc=getIndexJobConf(indexName,relationType);
  copyPropertiesToInputAndOutputConf(hadoopBaseConf,janusgraphProperties);
  cr.scanJobConf(mc);
  cr.scanJobConfRoot(GraphDatabaseConfiguration.class.getName() + "#JOB_NS");
  cr.baseHadoopConf(hadoopBaseConf);
  return cr.run();
}
