/** 
 * Record request size and response size
 * @param mixinMetric MixinMetric
 * @param model       information model
 */
private void recordSize(MixinMetric mixinMetric,RpcClientLookoutModel model){
  Long requestSize=model.getRequestSize();
  Long responseSize=model.getResponseSize();
  if (requestSize != null) {
    DistributionSummary requestSizeDS=mixinMetric.distributionSummary("request_size");
    requestSizeDS.record(model.getRequestSize());
  }
  if (responseSize != null) {
    DistributionSummary responseSizeDS=mixinMetric.distributionSummary("response_size");
    responseSizeDS.record(model.getResponseSize());
  }
}
