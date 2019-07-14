/** 
 * Looks at the list of  {@code annotations} and see if there is any annotation which exists {@code exemptingAnnotations}.
 */
private static boolean exemptedByAnnotation(List<? extends AnnotationTree> annotations,VisitorState state){
  for (  AnnotationTree annotation : annotations) {
    if (((JCAnnotation)annotation).type != null) {
      TypeSymbol tsym=((JCAnnotation)annotation).type.tsym;
      if (EXEMPTING_VARIABLE_ANNOTATIONS.contains(tsym.getQualifiedName().toString())) {
        return true;
      }
    }
  }
  return false;
}
