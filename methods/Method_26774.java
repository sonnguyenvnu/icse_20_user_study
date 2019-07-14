public static BugPatternInstance fromElement(Element element){
  BugPatternInstance instance=new BugPatternInstance();
  instance.className=element.toString();
  BugPattern annotation=element.getAnnotation(BugPattern.class);
  instance.name=annotation.name();
  instance.altNames=annotation.altNames();
  instance.tags=annotation.tags();
  instance.severity=annotation.severity();
  instance.summary=annotation.summary();
  instance.explanation=annotation.explanation();
  instance.documentSuppression=annotation.documentSuppression();
  instance.providesFix=annotation.providesFix();
  Map<String,Object> keyValues=getAnnotation(element,BugPattern.class.getName());
  Object suppression=keyValues.get("suppressionAnnotations");
  if (suppression == null) {
    instance.suppressionAnnotations=new String[]{SuppressWarnings.class.getName()};
  }
 else {
    Preconditions.checkState(suppression instanceof List);
    @SuppressWarnings("unchecked") List<? extends AnnotationValue> resultList=(List<? extends AnnotationValue>)suppression;
    instance.suppressionAnnotations=resultList.stream().map(AnnotationValue::toString).toArray(String[]::new);
  }
  instance.generateExamplesFromTestCases=!keyValues.containsKey("generateExamplesFromTestCases") || (boolean)keyValues.get("generateExamplesFromTestCases");
  return instance;
}
