public boolean highlightText(String text,boolean caseSensitive){
  if (text.length() > 1) {
    textArea.setMarkAllHighlightColor(SEARCH_HIGHLIGHT_COLOR);
    textArea.setCaretPosition(textArea.getSelectionStart());
    SearchContext context=newSearchContext(text,caseSensitive,false,true,false);
    SearchResult result=SearchEngine.find(textArea,context);
    if (!result.wasFound()) {
      textArea.setCaretPosition(0);
      result=SearchEngine.find(textArea,context);
    }
    return result.wasFound();
  }
 else {
    return true;
  }
}
