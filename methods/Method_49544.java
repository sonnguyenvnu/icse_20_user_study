private static ModifiableConfiguration getIndexJobConf(String indexName,String relationType){
  ModifiableConfiguration mc=new ModifiableConfiguration(GraphDatabaseConfiguration.JOB_NS,new CommonsConfiguration(new BaseConfiguration()),BasicConfiguration.Restriction.NONE);
  mc.set(org.janusgraph.graphdb.olap.job.IndexUpdateJob.INDEX_NAME,indexName);
  mc.set(org.janusgraph.graphdb.olap.job.IndexUpdateJob.INDEX_RELATION_TYPE,relationType);
  mc.set(GraphDatabaseConfiguration.JOB_START_TIME,System.currentTimeMillis());
  return mc;
}
