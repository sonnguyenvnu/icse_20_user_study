public static ScanMetrics cassandraRemove(Properties janusgraphProperties,String indexName,String relationType,String partitionerName) throws InterruptedException, IOException, ClassNotFoundException {
  return cassandraRemove(janusgraphProperties,indexName,relationType,partitionerName,new Configuration());
}
