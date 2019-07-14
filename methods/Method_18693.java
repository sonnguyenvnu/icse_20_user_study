public static Optional<PsiJavaFile> findComponentFile(String qualifiedSpecName,Project project){
  return Optional.of(qualifiedSpecName).map(LithoPluginUtils::getLithoComponentNameFromSpec).map(qualifiedComponentName -> JavaPsiFacade.getInstance(project).findClass(qualifiedComponentName,GlobalSearchScope.allScope(project))).map(PsiElement::getContainingFile).filter(PsiJavaFile.class::isInstance).map(PsiJavaFile.class::cast);
}
