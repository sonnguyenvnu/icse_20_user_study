/** 
 * Only works for classes in the module since AutoValue has a retention of Source so it is discarded after compilation.
 */
private boolean isAutoValueType(Element element){
  for (  AnnotationMirror annotationMirror : element.getAnnotationMirrors()) {
    DeclaredType annotationType=annotationMirror.getAnnotationType();
    boolean isAutoValue=isSubtypeOfType(annotationType,"com.google.auto.value.AutoValue");
    if (isAutoValue) {
      return true;
    }
  }
  return false;
}
