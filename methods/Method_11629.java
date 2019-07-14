@Override public void process(Page page){
  for (  ExtractRule extractRule : extractRules) {
    if (extractRule.isMulti()) {
      List<String> results=page.getHtml().selectDocumentForList(extractRule.getSelector());
      if (extractRule.isNotNull() && results.size() == 0) {
        page.setSkip(true);
      }
 else {
        page.getResultItems().put(extractRule.getFieldName(),results);
      }
    }
 else {
      String result=page.getHtml().selectDocument(extractRule.getSelector());
      if (extractRule.isNotNull() && result == null) {
        page.setSkip(true);
      }
 else {
        page.getResultItems().put(extractRule.getFieldName(),result);
      }
    }
  }
}
