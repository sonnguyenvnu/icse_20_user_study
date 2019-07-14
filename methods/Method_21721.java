protected void updateRequestWithHighlight(Select select,SearchRequestBuilder request){
  boolean foundAnyHighlights=false;
  HighlightBuilder highlightBuilder=new HighlightBuilder();
  for (  Hint hint : select.getHints()) {
    if (hint.getType() == HintType.HIGHLIGHT) {
      HighlightBuilder.Field highlightField=parseHighlightField(hint.getParams());
      if (highlightField != null) {
        foundAnyHighlights=true;
        highlightBuilder.field(highlightField);
      }
    }
  }
  if (foundAnyHighlights) {
    request.highlighter(highlightBuilder);
  }
}
