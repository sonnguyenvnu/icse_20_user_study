private List<SearchHit> fetchAllHits(TableInJoinRequestBuilder tableInJoinRequest){
  Integer hintLimit=tableInJoinRequest.getHintLimit();
  SearchRequestBuilder requestBuilder=tableInJoinRequest.getRequestBuilder();
  if (hintLimit != null && hintLimit < MAX_RESULTS_ON_ONE_FETCH) {
    requestBuilder.setSize(hintLimit);
    SearchResponse searchResponse=requestBuilder.get();
    updateMetaSearchResults(searchResponse);
    return Arrays.asList(searchResponse.getHits().getHits());
  }
  return scrollTillLimit(tableInJoinRequest,hintLimit);
}
