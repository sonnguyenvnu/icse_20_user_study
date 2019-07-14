public static MethodInterceptorHolder setCurrent(MethodInterceptorHolder holder){
  return ThreadLocalUtils.put(MethodInterceptorHolder.class.getName(),holder);
}
