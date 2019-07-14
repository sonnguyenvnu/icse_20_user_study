@Override protected Annotation[] getRunnerAnnotations(){
  Annotation[] allAnnotations=super.getRunnerAnnotations();
  Annotation[] annotationsWithoutRunWith=new Annotation[allAnnotations.length - 1];
  int i=0;
  for (  Annotation annotation : allAnnotations) {
    if (!annotation.annotationType().equals(RunWith.class)) {
      annotationsWithoutRunWith[i]=annotation;
      ++i;
    }
  }
  return annotationsWithoutRunWith;
}
