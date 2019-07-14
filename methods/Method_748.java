@SuppressWarnings("unchecked") public <T extends Annotation>T getAnnation(Class<T> annotationClass){
  if (annotationClass == JSONField.class) {
    return (T)getAnnotation();
  }
  T annotatition=null;
  if (method != null) {
    annotatition=method.getAnnotation(annotationClass);
  }
  if (annotatition == null && field != null) {
    annotatition=field.getAnnotation(annotationClass);
  }
  return annotatition;
}
