private void recordSliceMetrics(StoreTransaction txh,List<Entry> row){
  if (!txh.getConfiguration().hasGroupName())   return;
  String p=txh.getConfiguration().getGroupName();
  final MetricManager mgr=MetricManager.INSTANCE;
  mgr.getCounter(p,metricsStoreName,M_GET_SLICE,M_ENTRIES_COUNT).inc(row.size());
  mgr.getHistogram(p,metricsStoreName,M_GET_SLICE,M_ENTRIES_HISTO).update(row.size());
}
