/** 
 * @param sourceElement component to find declaration for
 * @param isComponentClass filters component type
 * @param hasComponentSpecAnnotation filters resolved componentSpec type
 * @param event event tag for logging
 * @return declaration target for the provided sourceElement or null if it wasn't found
 */
@Nullable static PsiElement getGotoDeclarationTarget(@Nullable PsiElement sourceElement,Predicate<PsiClass> isComponentClass,Predicate<PsiClass> hasComponentSpecAnnotation,String event){
  if (sourceElement == null || PsiTreeUtil.getParentOfType(sourceElement,PsiImportStatement.class) != null) {
    return null;
  }
  final Project project=sourceElement.getProject();
  return resolve(sourceElement).filter(PsiClass.class::isInstance).map(PsiClass.class::cast).filter(isComponentClass).map(PsiClass::getQualifiedName).filter(Objects::nonNull).map(LithoPluginUtils::getLithoComponentSpecNameFromComponent).flatMap(specName -> {
    GlobalSearchScope scope=GlobalSearchScope.everythingScope(project);
    return Stream.of(JavaPsiFacade.getInstance(project).findClasses(specName,scope));
  }
).filter(hasComponentSpecAnnotation).limit(1).peek(psiClass -> logger.log(event)).findFirst().orElse(null);
}
