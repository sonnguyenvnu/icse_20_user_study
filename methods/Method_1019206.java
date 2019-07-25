@Override public JavaRDD<WriteStatus> compact(JavaSparkContext jsc,String compactionInstantTime,HoodieCompactionPlan compactionPlan){
  throw new HoodieNotSupportedException("Compaction is not supported from a CopyOnWrite table");
}
