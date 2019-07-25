@Override public HaloMetricResponse invoke(){
  HaloMetricResponse haloMetricResponse=new HaloMetricResponse();
  Map<String,Object> result=new LinkedHashMap<String,Object>();
  List<PublicMetrics> metrics=new ArrayList<PublicMetrics>(this.publicMetrics);
  for (  PublicMetrics publicMetric : metrics) {
    try {
      for (      Metric<?> metric : publicMetric.metrics()) {
        if (THREADS.equals(metric.getName())) {
          haloMetricResponse.setJvmThreadslive(String.valueOf(metric.getValue()));
        }
        if (NON_HEAP_Used.equals(metric.getName())) {
          haloMetricResponse.setJvmMemoryUsedNonHeap(String.valueOf(metric.getValue()));
        }
        if (HEAP_USED.equals(metric.getName())) {
          haloMetricResponse.setJvmMemoryUsedHeap(String.valueOf(metric.getValue()));
        }
        if (HEAP_COMMITTED.equals(metric.getName())) {
          haloMetricResponse.setHeapCommitted(String.valueOf(metric.getValue()));
        }
        if (HEAP_INIT.equals(metric.getName())) {
          haloMetricResponse.setHeapInit(String.valueOf(metric.getValue()));
        }
        if (HEAP_MAX.equals(metric.getName())) {
          haloMetricResponse.setHeapMax(String.valueOf(metric.getValue()));
        }
        getGcInfo(haloMetricResponse,metric);
        if (NONHEAP_COMMITTED.equals(metric.getName())) {
          haloMetricResponse.setNonheapCommitted(String.valueOf(metric.getValue()));
        }
        if (SYSTEMLOAD_AVERAGE.equals(metric.getName())) {
          haloMetricResponse.setSystemloadAverage(String.valueOf(metric.getValue()));
        }
        if (PROCESSORS.equals(metric.getName())) {
          haloMetricResponse.setProcessors(String.valueOf(metric.getValue()));
        }
      }
    }
 catch (    Exception ex) {
    }
  }
  return haloMetricResponse;
}
