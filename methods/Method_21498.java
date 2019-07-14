private List<SearchHit> createCombinedResults(TableInJoinRequestBuilder secondTableRequest){
  List<SearchHit> combinedResult=new ArrayList<>();
  int resultIds=0;
  int totalLimit=this.requestBuilder.getTotalLimit();
  Integer hintLimit=secondTableRequest.getHintLimit();
  SearchResponse searchResponse;
  boolean finishedScrolling;
  if (hintLimit != null && hintLimit < MAX_RESULTS_ON_ONE_FETCH) {
    searchResponse=secondTableRequest.getRequestBuilder().setSize(hintLimit).get();
    finishedScrolling=true;
  }
 else {
    searchResponse=secondTableRequest.getRequestBuilder().setScroll(new TimeValue(60000)).setSize(MAX_RESULTS_ON_ONE_FETCH).get();
    finishedScrolling=false;
  }
  updateMetaSearchResults(searchResponse);
  boolean limitReached=false;
  int fetchedSoFarFromSecondTable=0;
  while (!limitReached) {
    SearchHit[] secondTableHits=searchResponse.getHits().getHits();
    fetchedSoFarFromSecondTable+=secondTableHits.length;
    for (    SearchHit secondTableHit : secondTableHits) {
      if (limitReached)       break;
      HashMap<String,List<Map.Entry<Field,Field>>> comparisons=this.hashJoinComparisonStructure.getComparisons();
      for (      Map.Entry<String,List<Map.Entry<Field,Field>>> comparison : comparisons.entrySet()) {
        String comparisonID=comparison.getKey();
        List<Map.Entry<Field,Field>> t1ToT2FieldsComparison=comparison.getValue();
        String key=getComparisonKey(t1ToT2FieldsComparison,secondTableHit,false,null);
        SearchHitsResult searchHitsResult=this.hashJoinComparisonStructure.searchForMatchingSearchHits(comparisonID,key);
        if (searchHitsResult != null && searchHitsResult.getSearchHits().size() > 0) {
          searchHitsResult.setMatchedWithOtherTable(true);
          List<SearchHit> searchHits=searchHitsResult.getSearchHits();
          for (          SearchHit matchingHit : searchHits) {
            String combinedId=matchingHit.getId() + "|" + secondTableHit.getId();
            if (this.alreadyMatched.contains(combinedId)) {
              continue;
            }
 else {
              this.alreadyMatched.add(combinedId);
            }
            Map<String,Object> copiedSource=new HashMap<String,Object>();
            copyMaps(copiedSource,secondTableHit.getSourceAsMap());
            onlyReturnedFields(copiedSource,secondTableRequest.getReturnedFields(),secondTableRequest.getOriginalSelect().isSelectAll());
            SearchHit searchHit=new SearchHit(matchingHit.docId(),combinedId,new Text(matchingHit.getType() + "|" + secondTableHit.getType()),matchingHit.getFields());
            searchHit.sourceRef(matchingHit.getSourceRef());
            searchHit.getSourceAsMap().clear();
            searchHit.getSourceAsMap().putAll(matchingHit.getSourceAsMap());
            String t1Alias=requestBuilder.getFirstTable().getAlias();
            String t2Alias=requestBuilder.getSecondTable().getAlias();
            mergeSourceAndAddAliases(copiedSource,searchHit,t1Alias,t2Alias);
            combinedResult.add(searchHit);
            resultIds++;
            if (resultIds >= totalLimit) {
              limitReached=true;
              break;
            }
          }
        }
      }
    }
    if (!finishedScrolling) {
      if (secondTableHits.length > 0 && (hintLimit == null || fetchedSoFarFromSecondTable >= hintLimit)) {
        searchResponse=client.prepareSearchScroll(searchResponse.getScrollId()).setScroll(new TimeValue(600000)).execute().actionGet();
      }
 else       break;
    }
 else {
      break;
    }
  }
  return combinedResult;
}
