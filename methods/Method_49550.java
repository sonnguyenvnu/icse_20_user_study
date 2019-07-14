static ModifiableConfiguration getJanusGraphConfiguration(Context context){
  org.apache.hadoop.conf.Configuration hadoopConf=DEFAULT_COMPAT.getContextConfiguration(context);
  return ModifiableHadoopConfiguration.of(JanusGraphHadoopConfiguration.MAPRED_NS,hadoopConf).getJanusGraphConf();
}
