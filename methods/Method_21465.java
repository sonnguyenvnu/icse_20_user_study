protected void updateMetaSearchResults(SearchResponse searchResponse){
  this.metaResults.addSuccessfulShards(searchResponse.getSuccessfulShards());
  this.metaResults.addFailedShards(searchResponse.getFailedShards());
  this.metaResults.addTotalNumOfShards(searchResponse.getTotalShards());
  this.metaResults.updateTimeOut(searchResponse.isTimedOut());
}
