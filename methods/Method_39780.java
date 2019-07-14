/** 
 * Returns <code>true</code> if class is annotated with one of provided annotation.
 */
default boolean hasAnnotation(final Class<? extends Annotation>... an){
  AnnotationInfo[] anns=getAnnotations();
  if (anns == null) {
    return false;
  }
  for (  Class<? extends Annotation> annotationClass : an) {
    String anName=annotationClass.getName();
    for (    AnnotationInfo ann : anns) {
      if (ann.getAnnotationClassname().equals(anName)) {
        return true;
      }
    }
  }
  return false;
}
