private List<String> createHeadersAndFillDocsMap(boolean flat,SearchHit[] hits,String scrollId,List<Map<String,Object>> docsAsMap){
  Set<String> csvHeaders=new HashSet<>();
  for (  SearchHit hit : hits) {
    Map<String,Object> doc=hit.getSourceAsMap();
    Map<String,DocumentField> fields=hit.getFields();
    for (    DocumentField searchHitField : fields.values()) {
      doc.put(searchHitField.getName(),searchHitField.getValue());
    }
    mergeHeaders(csvHeaders,doc,flat);
    if (this.includeId) {
      doc.put("_id",hit.getId());
    }
    if (this.includeScore) {
      doc.put("_score",hit.getScore());
    }
    if (this.includeType) {
      doc.put("_type",hit.getType());
    }
    if (this.includeScrollId) {
      doc.put("_scroll_id",scrollId);
    }
    docsAsMap.add(doc);
  }
  ArrayList<String> headersList=new ArrayList<>(csvHeaders);
  if (this.includeId) {
    headersList.add("_id");
  }
  if (this.includeScore) {
    headersList.add("_score");
  }
  if (this.includeType) {
    headersList.add("_type");
  }
  if (this.includeScrollId) {
    headersList.add("_scroll_id");
  }
  return headersList;
}
