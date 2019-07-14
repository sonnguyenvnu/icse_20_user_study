private void updateRequestWithTermsFilter(Map<String,Map<String,List<Object>>> optimizationTermsFilterStructure,TableInJoinRequestBuilder secondTableRequest) throws SqlParseException {
  Select select=secondTableRequest.getOriginalSelect();
  BoolQueryBuilder orQuery=QueryBuilders.boolQuery();
  for (  Map<String,List<Object>> optimization : optimizationTermsFilterStructure.values()) {
    BoolQueryBuilder andQuery=QueryBuilders.boolQuery();
    for (    Map.Entry<String,List<Object>> keyToValues : optimization.entrySet()) {
      String fieldName=keyToValues.getKey();
      List<Object> values=keyToValues.getValue();
      andQuery.must(QueryBuilders.termsQuery(fieldName,values));
    }
    orQuery.should(andQuery);
  }
  Where where=select.getWhere();
  BoolQueryBuilder boolQuery;
  if (where != null) {
    boolQuery=QueryMaker.explan(where,false);
    boolQuery.must(orQuery);
  }
 else   boolQuery=orQuery;
  secondTableRequest.getRequestBuilder().setQuery(boolQuery);
}
