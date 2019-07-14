@JsonProperty("expressions") protected void setExpressions(TopList newExpressions){
  if (newExpressions != null) {
    _preferenceStore.put("scripting.expressions",newExpressions);
  }
}
