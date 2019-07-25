private void format(Editable editable,int start){
  SyntaxUtils.removeSpans(editable,start,RelativeSizeSpan.class);
  Syntax syntax=EditFactory.create().getHeaderSyntax(mMarkdownConfiguration);
  List<EditToken> editTokenList=SyntaxUtils.getMatchedEditTokenList(editable,syntax.format(editable),start);
  SyntaxUtils.setSpans(editable,editTokenList);
}
