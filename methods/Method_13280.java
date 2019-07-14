public void findNext(String text,boolean caseSensitive){
  if (text.length() > 1) {
    textArea.setMarkAllHighlightColor(SEARCH_HIGHLIGHT_COLOR);
    SearchContext context=newSearchContext(text,caseSensitive,false,true,false);
    SearchResult result=SearchEngine.find(textArea,context);
    if (!result.wasFound()) {
      textArea.setCaretPosition(0);
      SearchEngine.find(textArea,context);
    }
  }
}
