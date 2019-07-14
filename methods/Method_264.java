private static boolean isFieldRequired(Element element){
  return !hasAnnotationWithName(element,NULLABLE_ANNOTATION_NAME);
}
