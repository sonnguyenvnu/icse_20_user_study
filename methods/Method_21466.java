protected SearchResponse scrollOneTimeWithMax(Client client,TableInJoinRequestBuilder tableRequest){
  SearchResponse responseWithHits;
  SearchRequestBuilder scrollRequest=tableRequest.getRequestBuilder().setScroll(new TimeValue(60000)).setSize(MAX_RESULTS_ON_ONE_FETCH);
  boolean ordered=tableRequest.getOriginalSelect().isOrderdSelect();
  if (!ordered)   scrollRequest.addSort(FieldSortBuilder.DOC_FIELD_NAME,SortOrder.ASC);
  responseWithHits=scrollRequest.get();
  return responseWithHits;
}
