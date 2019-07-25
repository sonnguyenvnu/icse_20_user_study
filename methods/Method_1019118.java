private void format(Editable editable,int start){
  SyntaxUtils.removeSpans(editable,start,MDQuoteSpan.class);
  Syntax syntax=EditFactory.create().getBlockQuotesSyntax(mMarkdownConfiguration);
  List<EditToken> editTokenList=SyntaxUtils.getMatchedEditTokenList(editable,syntax.format(editable),start);
  SyntaxUtils.setSpans(editable,editTokenList);
}
