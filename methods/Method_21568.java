private List<String> createHeadersAndFillDocsMap(boolean flat,SearchHit[] hits,String scrollId,List<Map<String,Object>> docsAsMap){
  Set<String> headers=new LinkedHashSet<>();
  if (this.queryAction instanceof DefaultQueryAction) {
    headers.addAll(((DefaultQueryAction)this.queryAction).getFieldNames());
  }
  boolean hasScrollId=this.includeScrollId || headers.contains("_scroll_id");
  for (  SearchHit hit : hits) {
    Map<String,Object> doc=hit.getSourceAsMap();
    Map<String,DocumentField> fields=hit.getFields();
    for (    DocumentField searchHitField : fields.values()) {
      doc.put(searchHitField.getName(),searchHitField.getValue());
    }
    if (this.includeScore) {
      doc.put("_score",hit.getScore());
    }
    if (this.includeType) {
      doc.put("_type",hit.getType());
    }
    if (this.includeId) {
      doc.put("_id",hit.getId());
    }
    if (hasScrollId) {
      doc.put("_scroll_id",scrollId);
    }
    mergeHeaders(headers,doc,flat);
    docsAsMap.add(doc);
  }
  return new ArrayList<>(headers);
}
