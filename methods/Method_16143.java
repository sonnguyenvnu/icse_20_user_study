@Override public boolean support(Class clazz,Method method){
  AccessLogger ann=AnnotationUtils.findAnnotation(method,AccessLogger.class);
  return null != ann && !ann.ignore();
}
