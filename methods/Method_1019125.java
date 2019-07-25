private void format(Editable editable,int start){
  SyntaxUtils.removeSpans(editable,start,StrikethroughSpan.class);
  Syntax syntax=EditFactory.create().getStrikeThroughSyntax(mMarkdownConfiguration);
  List<EditToken> editTokenList=SyntaxUtils.getMatchedEditTokenList(editable,syntax.format(editable),start);
  SyntaxUtils.setSpans(editable,editTokenList);
}
