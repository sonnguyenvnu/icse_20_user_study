@Override public void visit(@NotNull PsiElement psiElement,@NotNull String option,@NotNull FormClass formClass,@NotNull FormOptionEnum optionEnum){
  String typeText=formClass.getPhpClass().getPresentableFQN();
  if (typeText.lastIndexOf("\\") != -1) {
    typeText=typeText.substring(typeText.lastIndexOf("\\") + 1);
  }
  if (typeText.endsWith("Type")) {
    typeText=typeText.substring(0,typeText.length() - 4);
  }
  lookupElements.add(LookupElementBuilder.create(option).withTypeText(typeText,true).withIcon(Symfony2Icons.FORM_OPTION));
}
