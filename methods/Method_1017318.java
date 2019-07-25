@Override public void invoke(@NotNull Project project,@NotNull PsiFile psiFile,@Nullable Editor editor,@NotNull PsiElement psiElement,@NotNull PsiElement psiElement1){
  if (editor == null) {
    return;
  }
  Collection<PhpClass> anyByFQN=PhpIndex.getInstance(project).getAnyByFQN(this.expectedClass);
  if (anyByFQN.size() == 0) {
    return;
  }
  Collection<ContainerService> suggestions=ServiceUtil.getServiceSuggestionForPhpClass(anyByFQN.iterator().next(),ContainerCollectionResolver.getServices(project));
  if (suggestions.size() == 0) {
    HintManager.getInstance().showErrorHint(editor,"No suggestion found");
    return;
  }
  ServiceSuggestDialog.create(editor,ContainerUtil.map(suggestions,ContainerService::getName),new MyInsertCallback(editor,psiElement));
}
