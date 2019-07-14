@Override public boolean matches(Method method,Class<?> aClass){
  boolean support=AopUtils.findAnnotation(aClass,Controller.class) != null || AopUtils.findAnnotation(aClass,RestController.class) != null || AopUtils.findAnnotation(aClass,method,Authorize.class) != null;
  if (support && autoParse) {
    defaultParser.parse(aClass,method);
  }
  return support;
}
