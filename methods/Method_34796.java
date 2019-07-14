public static boolean isCurrentThreadInitialized(){
  HystrixRequestContext context=requestVariables.get();
  return context != null && context.state != null;
}
