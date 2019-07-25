@Override public void visit(@NotNull PsiElement psiElement,@NotNull String option,@NotNull FormClass formClass,@NotNull FormOptionEnum optionEnum){
  if (option.equalsIgnoreCase(optionName)) {
    psiElements.add(psiElement);
  }
}
