/** 
 * Validates this path over the given aggregator as a point of reference.
 * @param root The point of reference of this path
 * @throws AggregationExecutionException on validation error
 */
public void validate(Aggregator root) throws AggregationExecutionException {
  Aggregator aggregator=root;
  for (int i=0; i < pathElements.size(); i++) {
    aggregator=aggregator.subAggregator(pathElements.get(i).name);
    if (aggregator == null) {
      throw new AggregationExecutionException("Invalid aggregator order path [" + this + "]. Unknown aggregation [" + pathElements.get(i).name + "]");
    }
    if (i < pathElements.size() - 1) {
      if (!(aggregator instanceof SingleBucketAggregator)) {
        throw new AggregationExecutionException("Invalid aggregation order path [" + this + "]. Buckets can only be sorted on a sub-aggregator path " + "that is built out of zero or more single-bucket aggregations within the path and a final " + "single-bucket or a metrics aggregation at the path end. Sub-path [" + subPath(0,i + 1) + "] points to non single-bucket aggregation");
      }
      if (pathElements.get(i).key != null) {
        throw new AggregationExecutionException("Invalid aggregation order path [" + this + "]. Buckets can only be sorted on a sub-aggregator path " + "that is built out of zero or more single-bucket aggregations within the path and a " + "final single-bucket or a metrics aggregation at the path end. Sub-path [" + subPath(0,i + 1) + "] points to non single-bucket aggregation");
      }
    }
  }
  boolean singleBucket=aggregator instanceof SingleBucketAggregator;
  if (!singleBucket && !(aggregator instanceof NumericMetricsAggregator)) {
    throw new AggregationExecutionException("Invalid aggregation order path [" + this + "]. Buckets can only be sorted on a sub-aggregator path " + "that is built out of zero or more single-bucket aggregations within the path and a final " + "single-bucket or a metrics aggregation at the path end.");
  }
  AggregationPath.PathElement lastToken=lastPathElement();
  if (singleBucket) {
    if (lastToken.key != null && !"doc_count".equals(lastToken.key)) {
      throw new AggregationExecutionException("Invalid aggregation order path [" + this + "]. Ordering on a single-bucket aggregation can only be done on its doc_count. " + "Either drop the key (a la \"" + lastToken.name + "\") or change it to \"doc_count\" (a la \"" + lastToken.name + ".doc_count\")");
    }
    return;
  }
  if (aggregator instanceof NumericMetricsAggregator.SingleValue) {
    if (lastToken.key != null && !"value".equals(lastToken.key)) {
      throw new AggregationExecutionException("Invalid aggregation order path [" + this + "]. Ordering on a single-value metrics aggregation can only be done on its value. " + "Either drop the key (a la \"" + lastToken.name + "\") or change it to \"value\" (a la \"" + lastToken.name + ".value\")");
    }
    return;
  }
  if (lastToken.key == null) {
    throw new AggregationExecutionException("Invalid aggregation order path [" + this + "]. When ordering on a multi-value metrics aggregation a metric name must be specified");
  }
  if (!((NumericMetricsAggregator.MultiValue)aggregator).hasMetric(lastToken.key)) {
    throw new AggregationExecutionException("Invalid aggregation order path [" + this + "]. Unknown metric name [" + lastToken.key + "] on multi-value metrics aggregation [" + lastToken.name + "]");
  }
}
