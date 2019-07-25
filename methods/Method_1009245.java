@Override public void invoke(@NotNull Project project,@NotNull PsiFile file,@NotNull PsiElement startElement,@NotNull PsiElement endElement){
  assert startElement == endElement;
  com.intellij.lang.ASTNode parentNode=startElement.getNode();
  com.intellij.lang.ASTNode whiteSpace=parentNode.findChildByType(TokenType.WHITE_SPACE);
  BlockSupport blockSupport=BlockSupport.getInstance(project);
  final int startOffset=whiteSpace.getStartOffset();
  final int endOffset=startOffset + whiteSpace.getTextLength();
  blockSupport.reparseRange(file,startOffset,endOffset,"");
}
