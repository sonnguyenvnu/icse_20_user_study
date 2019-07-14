@Override public void incrementCustom(String metric,long delta){
  counters.getGroup(HadoopContextScanMetrics.CUSTOM_COUNTER_GROUP).findCounter(metric).increment(delta);
}
