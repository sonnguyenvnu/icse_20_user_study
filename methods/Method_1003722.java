private static void intercept(Execution execution,final Iterator<? extends ExecInterceptor> interceptors,Runnable runnable) throws Exception {
  if (interceptors.hasNext()) {
    interceptors.next().intercept(execution,ExecInterceptor.ExecType.BLOCKING,() -> intercept(execution,interceptors,runnable));
  }
 else {
    runnable.run();
  }
}
