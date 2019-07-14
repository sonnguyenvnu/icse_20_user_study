@Override protected List<AnnotationReplacements> annotationReplacements(){
  return Arrays.asList(new AnnotationReplacements(JUNIT_AFTER_ANNOTATION,JUNIT_BEFORE_ANNOTATION),new AnnotationReplacements(JUNIT_AFTER_CLASS_ANNOTATION,JUNIT_BEFORE_CLASS_ANNOTATION));
}
