public static SearchResponse scrollOneTimeWithHits(Client client,SearchRequestBuilder requestBuilder,Select originalSelect,int resultSize){
  SearchResponse responseWithHits;
  SearchRequestBuilder scrollRequest=requestBuilder.setScroll(new TimeValue(60000)).setSize(resultSize);
  boolean ordered=originalSelect.isOrderdSelect();
  if (!ordered)   scrollRequest.addSort(FieldSortBuilder.DOC_FIELD_NAME,SortOrder.ASC);
  responseWithHits=scrollRequest.get();
  return responseWithHits;
}
