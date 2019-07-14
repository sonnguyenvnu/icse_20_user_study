private ScanMetrics executeOnNonPartitionedVertices(int iteration,StandardScanner.Builder scanBuilder) throws InterruptedException, ExecutionException, BackendException {
  ScanMetrics jobResult=scanBuilder.execute().get();
  long failures=jobResult.get(ScanMetrics.Metric.FAILURE);
  if (failures > 0) {
    throw new JanusGraphException("Failed to process [" + failures + "] vertices in vertex program iteration " + "[" + iteration + "]. Computer is aborting.");
  }
  return jobResult;
}
