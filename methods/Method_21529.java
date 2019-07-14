public static SearchResponse executeSearchAction(DefaultQueryAction searchQueryAction) throws SqlParseException {
  SqlElasticSearchRequestBuilder builder=searchQueryAction.explain();
  SearchResponse resp=(SearchResponse)builder.get();
  if (resp.getFailedShards() > 0) {
    if (resp.getSuccessfulShards() < 1) {
      throw new IllegalStateException("fail to search[" + builder + "], " + Arrays.toString(resp.getShardFailures()));
    }
    LOGGER.warn("The failures that occurred during the search[{}]: {}",builder,Arrays.toString(resp.getShardFailures()));
  }
  return resp;
}
