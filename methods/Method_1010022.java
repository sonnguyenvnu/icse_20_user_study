/** 
 * Handles QuickFix action invoked on  {@link IgnoreEntry}.
 * @param project      the {@link Project} containing the working file
 * @param psiFile      the {@link PsiFile} containing handled entry
 * @param startElement the {@link IgnoreEntry} that will be removed
 * @param endElement   the {@link PsiElement} which is ignored in invoked action
 */
@Override public void invoke(@NotNull Project project,@NotNull PsiFile psiFile,@NotNull PsiElement startElement,@NotNull PsiElement endElement){
  if (startElement instanceof IgnoreEntry) {
    Document document=PsiDocumentManager.getInstance(project).getDocument(psiFile);
    if (document != null) {
      int start=startElement.getStartOffsetInParent();
      String text=startElement.getText();
      String fixed=getFixedPath(text);
      document.replaceString(start,start + text.length(),fixed);
    }
  }
}
