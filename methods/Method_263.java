private static boolean hasAnnotationWithName(Element element,String simpleName){
  for (  AnnotationMirror mirror : element.getAnnotationMirrors()) {
    String annotationName=mirror.getAnnotationType().asElement().getSimpleName().toString();
    if (simpleName.equals(annotationName)) {
      return true;
    }
  }
  return false;
}
