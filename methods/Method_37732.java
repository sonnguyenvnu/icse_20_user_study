public Annotations<A> onClass(final Class type){
  A a=(A)type.getAnnotation(annotationClass);
  if (a != null) {
    annotations.add(a);
  }
  return this;
}
