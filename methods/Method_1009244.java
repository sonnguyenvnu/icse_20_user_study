@Override public void invoke(@NotNull Project project,@NotNull PsiFile file,@NotNull PsiElement startElement,@NotNull PsiElement endElement){
  assert startElement == endElement;
  BlockSupport blockSupport=BlockSupport.getInstance(project);
  TextRange textRange=startElement.getTextRange();
  int startOffset=textRange.getStartOffset();
  int endOffset=textRange.getEndOffset();
  blockSupport.reparseRange(file,startOffset,endOffset," ::");
}
