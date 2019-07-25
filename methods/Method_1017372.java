@Override public void invoke(@NotNull Project project,Editor editor,@NotNull PsiElement psiElement) throws IncorrectOperationException {
  Pair<String,String> pair=getKeyAndDomain(psiElement);
  if (pair == null) {
    return;
  }
  new TranslationKeyIntentionAndQuickFixAction(pair.getFirst(),pair.getSecond(),new MyKeyDomainNotExistingCollector()).invoke(project,editor,psiElement.getContainingFile());
}
