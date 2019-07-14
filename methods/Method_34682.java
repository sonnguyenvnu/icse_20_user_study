/** 
 * Clears all state from metrics. If new requests come in instances will be recreated and metrics started from scratch.
 */
static void reset(){
  for (  HystrixCommandMetrics metricsInstance : getInstances()) {
    metricsInstance.unsubscribeAll();
  }
  metrics.clear();
}
