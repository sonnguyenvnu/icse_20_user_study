@Override public void invoke(@NotNull Project project,Editor editor,PsiFile file,DataContext dataContext){
  PsiElement element=PsiElementRenameHandler.getElement(dataContext);
  if (null == element) {
    element=BaseRefactoringAction.getElementAtCaret(editor,file);
  }
  if (null != element) {
    RenamePsiElementProcessor processor=RenamePsiElementProcessor.forElement(element);
    element=processor.substituteElementToRename(element,editor);
  }
  if (null != element) {
    editor.getScrollingModel().scrollToCaret(ScrollType.MAKE_VISIBLE);
    PsiElement nameSuggestionContext=InjectedLanguageUtil.findElementAtNoCommit(file,editor.getCaretModel().getOffset());
    PsiElementRenameHandler.invoke(element,project,nameSuggestionContext,editor);
  }
}
