public MethodInterceptorContext createParamContext(Object invokeResult){
  return new MethodInterceptorContext(){
    @Override public Object getTarget(){
      return target;
    }
    @Override public Method getMethod(){
      return method;
    }
    @Override public <T>Optional<T> getParameter(    String name){
      if (args == null) {
        return Optional.empty();
      }
      return Optional.ofNullable((T)args.get(name));
    }
    @Override public <T extends Annotation>T getAnnotation(    Class<T> annClass){
      return findAnnotation(annClass);
    }
    @Override public Map<String,Object> getParams(){
      return getArgs();
    }
    @Override public Object getInvokeResult(){
      return invokeResult;
    }
  }
;
}
