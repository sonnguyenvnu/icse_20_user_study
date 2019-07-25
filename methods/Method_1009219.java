@NotNull private static String excerpt(@NotNull PsiFile containingFile,@NotNull PsiElement element){
  StringBuilder excerptBuilder=new StringBuilder();
  excerptBuilder.append('\n');
  excerptBuilder.append("### Excerpt\n");
  excerptBuilder.append('\n');
  excerptBuilder.append("```\n");
  excerptBuilder.append(element.getText());
  excerptBuilder.append('\n');
  excerptBuilder.append("```\n");
  FileViewProvider fileViewProvider=containingFile.getViewProvider();
  Document document=fileViewProvider.getDocument();
  if (document != null) {
    TextRange textRange=element.getTextRange();
    int startingLine=document.getLineNumber(textRange.getStartOffset());
    int endingLine=document.getLineNumber(textRange.getEndOffset());
    excerptBuilder.append(" Line(s) ");
    excerptBuilder.append(startingLine);
    excerptBuilder.append('-');
    excerptBuilder.append(endingLine);
    VirtualFile virtualFile=containingFile.getVirtualFile();
    if (virtualFile != null) {
      excerptBuilder.append(" in ");
      excerptBuilder.append(virtualFile.getPath());
    }
    excerptBuilder.append("\n");
  }
  return excerptBuilder.toString();
}
