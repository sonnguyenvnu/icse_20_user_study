public static Object getFieldValue(SearchHit hit,String field){
  return deepSearchInMap(hit.getSourceAsMap(),field);
}
