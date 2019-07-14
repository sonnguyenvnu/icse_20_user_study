private void replaceInvocationUrl(RestInvocation restInvocation,String signature){
  String invocationUrl=restInvocation.getInvocationUrl();
  String newInvocationUrl=invocationUrl.replace(PLACEHOLDER,signature);
  try {
    invocationUrlField.set(restInvocation,newInvocationUrl);
  }
 catch (  IllegalArgumentException|IllegalAccessException e) {
    throw new IllegalStateException("rescu library has been updated");
  }
}
