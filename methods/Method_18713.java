public static boolean isSectionClass(@Nullable PsiClass psiClass){
  return Optional.ofNullable(psiClass).map(PsiClass::getSuperClass).map(PsiClass::getQualifiedName).filter("com.facebook.litho.sections.Section"::equals).isPresent();
}
