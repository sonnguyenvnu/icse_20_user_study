public Annotations onMethod(final Method method){
  A a=method.getAnnotation(annotationClass);
  if (a != null) {
    annotations.add(a);
  }
  return this;
}
