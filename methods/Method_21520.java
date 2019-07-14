private Object getFieldValue(SearchHit hit,String fieldName){
  Map<String,Object> sourceAsMap=hit.getSourceAsMap();
  if (fieldName.contains(".")) {
    String[] split=fieldName.split("\\.");
    return Util.searchPathInMap(sourceAsMap,split);
  }
 else   if (sourceAsMap.containsKey(fieldName)) {
    return sourceAsMap.get(fieldName);
  }
  return null;
}
