private void format(Editable editable,int start){
  SyntaxUtils.removeSpans(editable,start,AlignmentSpan.Standard.class);
  Syntax syntax=EditFactory.create().getCenterAlignSyntax(mMarkdownConfiguration);
  List<EditToken> editTokenList=SyntaxUtils.getMatchedEditTokenList(editable,syntax.format(editable),start);
  SyntaxUtils.setSpans(editable,editTokenList);
}
