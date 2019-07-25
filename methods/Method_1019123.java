private void format(Editable editable,int start){
  SyntaxUtils.removeSpans(editable,start,MDHorizontalRulesSpan.class);
  Syntax syntax=EditFactory.create().getHorizontalRulesSyntax(mMarkdownConfiguration);
  List<EditToken> editTokenList=SyntaxUtils.getMatchedEditTokenList(editable,syntax.format(editable),start);
  SyntaxUtils.setSpans(editable,editTokenList);
}
