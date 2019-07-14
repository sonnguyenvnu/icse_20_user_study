private static void copyIndexJobKeys(org.apache.hadoop.conf.Configuration hadoopConf,String indexName,String relationType){
  hadoopConf.set(ConfigElement.getPath(JanusGraphHadoopConfiguration.SCAN_JOB_CONFIG_KEYS,true) + "." + ConfigElement.getPath(IndexUpdateJob.INDEX_NAME),indexName);
  hadoopConf.set(ConfigElement.getPath(JanusGraphHadoopConfiguration.SCAN_JOB_CONFIG_KEYS,true) + "." + ConfigElement.getPath(IndexUpdateJob.INDEX_RELATION_TYPE),relationType);
  hadoopConf.set(ConfigElement.getPath(JanusGraphHadoopConfiguration.SCAN_JOB_CONFIG_KEYS,true) + "." + ConfigElement.getPath(GraphDatabaseConfiguration.JOB_START_TIME),String.valueOf(System.currentTimeMillis()));
}
