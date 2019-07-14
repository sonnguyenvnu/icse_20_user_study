private int combineResultsFromMultiResponses(List<SearchHit> combinedResults,int totalLimit,int currentCombinedResults,SearchHit[] hits,int currentIndex,MultiSearchRequest multiSearchRequest){
  MultiSearchResponse.Item[] responses=client.multiSearch(multiSearchRequest).actionGet().getResponses();
  String t1Alias=nestedLoopsRequest.getFirstTable().getAlias();
  String t2Alias=nestedLoopsRequest.getSecondTable().getAlias();
  for (int j=0; j < responses.length && currentCombinedResults < totalLimit; j++) {
    SearchHit hitFromFirstTable=hits[currentIndex + j];
    onlyReturnedFields(hitFromFirstTable.getSourceAsMap(),nestedLoopsRequest.getFirstTable().getReturnedFields(),nestedLoopsRequest.getFirstTable().getOriginalSelect().isSelectAll());
    SearchResponse multiItemResponse=responses[j].getResponse();
    updateMetaSearchResults(multiItemResponse);
    SearchHits responseForHit=multiItemResponse.getHits();
    if (responseForHit.getHits().length == 0 && nestedLoopsRequest.getJoinType() == SQLJoinTableSource.JoinType.LEFT_OUTER_JOIN) {
      SearchHit unmachedResult=createUnmachedResult(nestedLoopsRequest.getSecondTable().getReturnedFields(),currentCombinedResults,t1Alias,t2Alias,hitFromFirstTable);
      combinedResults.add(unmachedResult);
      currentCombinedResults++;
      continue;
    }
    for (    SearchHit matchedHit : responseForHit.getHits()) {
      SearchHit searchHit=getMergedHit(currentCombinedResults,t1Alias,t2Alias,hitFromFirstTable,matchedHit);
      combinedResults.add(searchHit);
      currentCombinedResults++;
      if (currentCombinedResults >= totalLimit)       break;
    }
    if (currentCombinedResults >= totalLimit)     break;
  }
  return currentCombinedResults;
}
