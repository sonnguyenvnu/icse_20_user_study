@Override public boolean getFilterPartitionedVertices(){
  return scanConf.get(JanusGraphHadoopConfiguration.FILTER_PARTITIONED_VERTICES);
}
