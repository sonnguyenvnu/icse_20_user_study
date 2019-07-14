public static MethodInterceptorHolder current(){
  return ThreadLocalUtils.get(MethodInterceptorHolder.class.getName());
}
