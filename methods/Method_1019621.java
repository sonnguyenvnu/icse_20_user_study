/** 
 * Finds the next instance of the string/regular expression specified from the caret position.  If a match is found, it is selected in this text area.
 * @param textArea The text area in which to search.
 * @param context What to search for and all search options.
 * @return The result of the operation.
 * @throws PatternSyntaxException If this is a regular expression searchbut the search text is an invalid regular expression.
 * @see #replace(RTextArea,SearchContext)
 * @see #replaceAll(RTextArea,SearchContext)
 */
public static SearchResult find(JTextArea textArea,SearchContext context){
  if (textArea instanceof RTextArea || context.getMarkAll()) {
    ((RTextArea)textArea).clearMarkAllHighlights();
  }
  boolean doMarkAll=textArea instanceof RTextArea && context.getMarkAll();
  String text=context.getSearchFor();
  if (text == null || text.length() == 0) {
    if (doMarkAll) {
      List<DocumentRange> emptyRangeList=Collections.emptyList();
      ((RTextArea)textArea).markAll(emptyRangeList);
    }
    return new SearchResult();
  }
  Caret c=textArea.getCaret();
  boolean forward=context.getSearchForward();
  int start=forward ? Math.max(c.getDot(),c.getMark()) : Math.min(c.getDot(),c.getMark());
  String findIn=getFindInText(textArea,start,forward);
  if (findIn == null || findIn.length() == 0) {
    return new SearchResult();
  }
  int markAllCount=0;
  if (doMarkAll) {
    markAllCount=markAllImpl((RTextArea)textArea,context).getMarkedCount();
  }
  SearchResult result=SearchEngine.findImpl(findIn,context);
  if (result.wasFound() && !result.getMatchRange().isZeroLength()) {
    textArea.getCaret().setSelectionVisible(true);
    if (forward && start > -1) {
      result.getMatchRange().translate(start);
    }
    RSyntaxUtilities.selectAndPossiblyCenter(textArea,result.getMatchRange(),true);
  }
  result.setMarkedCount(markAllCount);
  return result;
}
