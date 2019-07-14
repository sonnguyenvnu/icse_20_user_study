public static <T extends Annotation>T findAnnotationInHierarchy(PsiModifierListOwner listOwner,Class<T> annotationClass){
  T basicProxy=AnnotationUtil.findAnnotationInHierarchy(listOwner,annotationClass);
  if (basicProxy == null) {
    return null;
  }
  T biggerProxy=(T)Proxy.newProxyInstance(annotationClass.getClassLoader(),new Class[]{annotationClass},new AnnotationProxyInvocationHandler(basicProxy,listOwner,annotationClass));
  return biggerProxy;
}
