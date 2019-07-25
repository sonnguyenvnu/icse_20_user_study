@Override public JavaRDD<WriteStatus> compact(JavaSparkContext jsc,String compactionInstantTime,HoodieCompactionPlan compactionPlan){
  HoodieRealtimeTableCompactor compactor=new HoodieRealtimeTableCompactor();
  try {
    return compactor.compact(jsc,compactionPlan,this,config,compactionInstantTime);
  }
 catch (  IOException e) {
    throw new HoodieCompactionException("Could not compact " + config.getBasePath(),e);
  }
}
