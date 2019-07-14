static Map<Method,Method> toFallbackMethod(Map<Method,MethodHandler> dispatch){
  Map<Method,Method> result=new LinkedHashMap<>();
  for (  Method method : dispatch.keySet()) {
    method.setAccessible(true);
    result.put(method,method);
  }
  return result;
}
