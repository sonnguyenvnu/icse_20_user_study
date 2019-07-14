public static Aggregations executeAggregationAction(AggregationQueryAction aggregationQueryAction) throws SqlParseException {
  SqlElasticSearchRequestBuilder select=aggregationQueryAction.explain();
  SearchResponse resp=(SearchResponse)select.get();
  if (resp.getFailedShards() > 0) {
    if (resp.getSuccessfulShards() < 1) {
      throw new IllegalStateException("fail to aggregation[" + select + "], " + Arrays.toString(resp.getShardFailures()));
    }
    LOGGER.warn("The failures that occurred during the aggregation[{}]: {}",select,Arrays.toString(resp.getShardFailures()));
  }
  return resp.getAggregations();
}
