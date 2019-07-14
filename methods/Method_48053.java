private static RetryPolicy getRetryPolicy(String serializedRetryPolicy) throws BackendException {
  String[] tokens=serializedRetryPolicy.split(",");
  String policyClassName=tokens[0];
  int argCount=tokens.length - 1;
  Integer[] args=new Integer[argCount];
  for (int i=1; i < tokens.length; i++) {
    args[i - 1]=Integer.valueOf(tokens[i]);
  }
  try {
    RetryPolicy rp=instantiate(policyClassName,args,serializedRetryPolicy);
    log.debug("Instantiated RetryPolicy object {} from config string \"{}\"",rp,serializedRetryPolicy);
    return rp;
  }
 catch (  Exception e) {
    throw new PermanentBackendException("Failed to instantiate Astyanax Retry Policy class",e);
  }
}
