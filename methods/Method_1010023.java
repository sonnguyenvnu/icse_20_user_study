/** 
 * Handles QuickFix action invoked on  {@link IgnoreSyntax}.
 * @param project      the {@link Project} containing the working file
 * @param file         the {@link PsiFile} containing handled entry
 * @param startElement the {@link IgnoreSyntax} that will be selected and replaced
 * @param endElement   the {@link PsiElement} which is ignored in invoked action
 */
@Override public void invoke(@NotNull Project project,@NotNull PsiFile file,@Nullable("is null when called from inspection") Editor editor,@NotNull PsiElement startElement,@NotNull PsiElement endElement){
  if (startElement instanceof IgnoreSyntax) {
    PsiElement value=((IgnoreSyntax)startElement).getValue();
    if (editor != null) {
      editor.getSelectionModel().setSelection(value.getTextOffset(),value.getTextOffset() + value.getTextLength());
    }
    new CodeCompletionHandlerBase(CompletionType.BASIC).invokeCompletion(project,editor);
  }
}
