public static ScanMetrics cassandraRemove(Properties janusgraphProperties,String indexName,String relationType,String partitionerName,Configuration hadoopBaseConf) throws InterruptedException, IOException, ClassNotFoundException {
  IndexRemoveJob job=new IndexRemoveJob();
  CassandraHadoopScanRunner cr=new CassandraHadoopScanRunner(job);
  ModifiableConfiguration mc=getIndexJobConf(indexName,relationType);
  copyPropertiesToInputAndOutputConf(hadoopBaseConf,janusgraphProperties);
  cr.partitionerOverride(partitionerName);
  cr.scanJobConf(mc);
  cr.scanJobConfRoot(GraphDatabaseConfiguration.class.getName() + "#JOB_NS");
  cr.baseHadoopConf(hadoopBaseConf);
  return cr.run();
}
