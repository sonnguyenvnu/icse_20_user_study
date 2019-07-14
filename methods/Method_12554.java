@Override protected boolean isHandler(Class<?> beanType){
  return AnnotatedElementUtils.hasAnnotation(beanType,AdminController.class);
}
