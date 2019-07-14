public List<SearchHit> innerRun() throws IOException, SqlParseException {
  Map<String,Map<String,List<Object>>> optimizationTermsFilterStructure=initOptimizationStructure();
  updateFirstTableLimitIfNeeded();
  TableInJoinRequestBuilder firstTableRequest=requestBuilder.getFirstTable();
  createKeyToResultsAndFillOptimizationStructure(optimizationTermsFilterStructure,firstTableRequest);
  TableInJoinRequestBuilder secondTableRequest=requestBuilder.getSecondTable();
  if (needToOptimize(optimizationTermsFilterStructure)) {
    updateRequestWithTermsFilter(optimizationTermsFilterStructure,secondTableRequest);
  }
  List<SearchHit> combinedResult=createCombinedResults(secondTableRequest);
  int currentNumOfResults=combinedResult.size();
  int totalLimit=requestBuilder.getTotalLimit();
  if (requestBuilder.getJoinType() == SQLJoinTableSource.JoinType.LEFT_OUTER_JOIN && currentNumOfResults < totalLimit) {
    String t1Alias=requestBuilder.getFirstTable().getAlias();
    String t2Alias=requestBuilder.getSecondTable().getAlias();
    addUnmatchedResults(combinedResult,this.hashJoinComparisonStructure.getAllSearchHits(),requestBuilder.getSecondTable().getReturnedFields(),currentNumOfResults,totalLimit,t1Alias,t2Alias);
  }
  if (firstTableRequest.getOriginalSelect().isOrderdSelect()) {
    Collections.sort(combinedResult,new Comparator<SearchHit>(){
      @Override public int compare(      SearchHit o1,      SearchHit o2){
        return o1.docId() - o2.docId();
      }
    }
);
  }
  return combinedResult;
}
