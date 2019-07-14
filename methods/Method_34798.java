public static void setContextOnCurrentThread(HystrixRequestContext state){
  requestVariables.set(state);
}
