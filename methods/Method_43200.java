@Override public String digestParams(RestInvocation restInvocation){
  return restInvocation.getPath();
}
