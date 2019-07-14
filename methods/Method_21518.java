private void fillSetFromHits(String fieldName,SearchHit[] hits,Set<Object> setToFill){
  for (  SearchHit hit : hits) {
    Object fieldValue=getFieldValue(hit,fieldName);
    if (fieldValue != null) {
      setToFill.add(fieldValue);
    }
  }
}
