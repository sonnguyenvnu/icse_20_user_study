@Override public boolean support(Class clazz,Method method){
  Api api=AnnotationUtils.findAnnotation(clazz,Api.class);
  ApiOperation operation=AnnotationUtils.findAnnotation(method,ApiOperation.class);
  return api != null || operation != null;
}
