public static boolean isComponentClass(PsiClass psiClass){
  return psiClass != null && psiClass.getSuperClass() != null && ("ComponentLifecycle".equals(psiClass.getSuperClass().getName()) || "com.facebook.litho.Component".equals(psiClass.getSuperClass().getQualifiedName()));
}
