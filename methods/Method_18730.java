/** 
 * We consider an annotation to be valid for extraction if it's not an internal annotation (i.e. is in the <code>com.facebook.litho</code> package and is not a source-only annotation.
 * @return Whether or not to extract the given annotation.
 */
private static boolean isValidAnnotation(Project project,PsiAnnotation psiAnnotation){
  final String text=psiAnnotation.getQualifiedName();
  final PsiClass[] foundClasses=PsiShortNamesCache.getInstance(project).getClassesByName(text.substring(text.lastIndexOf('.') + 1),GlobalSearchScope.allScope(project));
  if (foundClasses.length <= 0) {
    throw new RuntimeException("Annotation class not found, text is: " + text);
  }
  PsiClass annotationClass=null;
  for (  PsiClass psiClass : foundClasses) {
    if (psiClass.getQualifiedName().contains(text)) {
      annotationClass=psiClass;
      break;
    }
  }
  final Retention retention=PsiAnnotationProxyUtils.findAnnotationInHierarchy(annotationClass,Retention.class);
  if (retention != null && retention.value() == RetentionPolicy.SOURCE) {
    return false;
  }
  return !text.startsWith("com.facebook.");
}
