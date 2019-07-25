private void format(Editable editable,int start){
  SyntaxUtils.removeSpans(editable,start,MDCodeBlockSpan.class);
  Syntax syntax=EditFactory.create().getCodeBlockSyntax(mMarkdownConfiguration);
  List<EditToken> editTokenList=syntax.format(editable);
  SyntaxUtils.setCodeSpan(editable,editTokenList);
}
