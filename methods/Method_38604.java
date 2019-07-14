protected ProxyAspect createTxProxyAspects(final Class<? extends Annotation>[] annotations){
  return new ProxyAspect(AnnotationTxAdvice.class,((ProxyPointcut)MethodInfo::isPublicMethod).and(MethodWithAnnotationPointcut.of(annotations)));
}
