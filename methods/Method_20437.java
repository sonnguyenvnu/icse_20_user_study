@Nullable static <T extends Annotation>ClassName getClassParamFromAnnotation(Element annotatedElement,Class<T> annotationClass,String paramName,Types typeUtils){
  AnnotationMirror am=getAnnotationMirror(annotatedElement,annotationClass);
  if (am == null) {
    return null;
  }
  AnnotationValue av=getAnnotationValue(am,paramName);
  if (av == null) {
    return null;
  }
 else {
    Object value=av.getValue();
    if (value instanceof TypeMirror) {
      return ClassName.get((TypeElement)typeUtils.asElement((TypeMirror)value));
    }
    return null;
  }
}
