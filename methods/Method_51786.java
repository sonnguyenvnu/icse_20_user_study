private boolean isSuppressWarnings(){
  return "SuppressWarnings".equals(getAnnotationName()) || "java.lang.SuppressWarnings".equals(getAnnotationName());
}
