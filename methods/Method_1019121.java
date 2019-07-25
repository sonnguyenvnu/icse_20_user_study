private void format(Editable editable,int start){
  SyntaxUtils.removeSpans(editable,start,BackgroundColorSpan.class);
  Syntax syntax=EditFactory.create().getCodeSyntax(mMarkdownConfiguration);
  List<EditToken> editTokenList=SyntaxUtils.getMatchedEditTokenList(editable,syntax.format(editable),start);
  SyntaxUtils.setSpans(editable,editTokenList);
}
