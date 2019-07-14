/** 
 * Returns <code>true</code> if annotation is present on given annotated element. Should be called first, before using the read methods.
 */
public boolean hasAnnotationOn(final AnnotatedElement annotatedElement){
  return annotatedElement.isAnnotationPresent(annotationClass);
}
