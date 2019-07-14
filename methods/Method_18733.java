private static PsiAnnotation getPsiAnnotation(PsiModifierListOwner listOwner,Class<?> annotationClass){
  Set<String> annotationStrings=new LinkedHashSet<>();
  annotationStrings.add(annotationClass.getCanonicalName());
  return AnnotationUtil.findAnnotationInHierarchy(listOwner,annotationStrings);
}
