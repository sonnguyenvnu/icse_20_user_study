public boolean openUri(URI uri){
  String query=uri.getQuery();
  if (query != null) {
    Map<String,String> parameters=parseQuery(query);
    if (parameters.containsKey("lineNumber")) {
      String lineNumber=parameters.get("lineNumber");
      try {
        goToLineNumber(Integer.parseInt(lineNumber));
        return true;
      }
 catch (      NumberFormatException e) {
        assert ExceptionUtil.printStackTrace(e);
      }
    }
 else     if (parameters.containsKey("position")) {
      String position=parameters.get("position");
      try {
        int pos=Integer.parseInt(position);
        if (textArea.getDocument().getLength() > pos) {
          setCaretPositionAndCenter(new DocumentRange(pos,pos));
          return true;
        }
      }
 catch (      NumberFormatException e) {
        assert ExceptionUtil.printStackTrace(e);
      }
    }
 else     if (parameters.containsKey("highlightFlags")) {
      String highlightFlags=parameters.get("highlightFlags");
      if ((highlightFlags.indexOf('s') != -1) && parameters.containsKey("highlightPattern")) {
        textArea.setMarkAllHighlightColor(SELECT_HIGHLIGHT_COLOR);
        textArea.setCaretPosition(0);
        String searchFor=createRegExp(parameters.get("highlightPattern"));
        SearchContext context=newSearchContext(searchFor,true,false,true,true);
        SearchResult result=SearchEngine.find(textArea,context);
        if (result.getMatchRange() != null) {
          textArea.setCaretPosition(result.getMatchRange().getStartOffset());
        }
        return true;
      }
    }
  }
  return false;
}
