protected SearchContext newSearchContext(String searchFor,boolean matchCase,boolean wholeWord,boolean searchForward,boolean regexp){
  SearchContext context=new SearchContext(searchFor,matchCase);
  context.setMarkAll(true);
  context.setWholeWord(wholeWord);
  context.setSearchForward(searchForward);
  context.setRegularExpression(regexp);
  return context;
}
