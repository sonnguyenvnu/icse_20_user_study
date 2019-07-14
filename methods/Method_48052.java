private static RetryBackoffStrategy getRetryBackoffStrategy(String desc) throws PermanentBackendException {
  if (null == desc)   return null;
  String[] tokens=desc.split(",");
  String policyClassName=tokens[0];
  int argCount=tokens.length - 1;
  Integer[] args=new Integer[argCount];
  for (int i=1; i < tokens.length; i++) {
    args[i - 1]=Integer.valueOf(tokens[i]);
  }
  try {
    RetryBackoffStrategy rbs=instantiate(policyClassName,args,desc);
    log.debug("Instantiated RetryBackoffStrategy object {} from config string \"{}\"",rbs,desc);
    return rbs;
  }
 catch (  Exception e) {
    throw new PermanentBackendException("Failed to instantiate Astyanax RetryBackoffStrategy implementation",e);
  }
}
