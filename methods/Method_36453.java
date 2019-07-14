private boolean isAttributeMethod(Method method){
  return (method != null && method.getParameterTypes().length == 0 && method.getReturnType() != void.class);
}
