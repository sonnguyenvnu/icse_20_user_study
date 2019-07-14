Optional<PsiClass> getFirstLayoutSpec(PsiClass[] currentClasses){
  return Arrays.stream(currentClasses).filter(Objects::nonNull).filter(psiClass -> PsiAnnotationProxyUtils.findAnnotationInHierarchy(psiClass,LayoutSpec.class) != null).findFirst();
}
