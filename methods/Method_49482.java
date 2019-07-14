@Override public long getCustom(String metric){
  return counters.getGroup(HadoopContextScanMetrics.CUSTOM_COUNTER_GROUP).findCounter(metric).getValue();
}
