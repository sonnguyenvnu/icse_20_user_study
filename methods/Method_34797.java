public static HystrixRequestContext getContextForCurrentThread(){
  HystrixRequestContext context=requestVariables.get();
  if (context != null && context.state != null) {
    return context;
  }
 else {
    return null;
  }
}
