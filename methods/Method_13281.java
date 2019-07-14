public void findPrevious(String text,boolean caseSensitive){
  if (text.length() > 1) {
    textArea.setMarkAllHighlightColor(SEARCH_HIGHLIGHT_COLOR);
    SearchContext context=newSearchContext(text,caseSensitive,false,false,false);
    SearchResult result=SearchEngine.find(textArea,context);
    if (!result.wasFound()) {
      textArea.setCaretPosition(textArea.getDocument().getLength());
      SearchEngine.find(textArea,context);
    }
  }
}
