private AnnotationMirror getAnnotationMirror(Element element,TypeMirror annotationType){
  for (  AnnotationMirror mirror : element.getAnnotationMirrors()) {
    if (processingEnv.getTypeUtils().isSameType(mirror.getAnnotationType(),annotationType)) {
      return mirror;
    }
  }
  return null;
}
