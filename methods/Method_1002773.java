/** 
 * Invokes the specified  {@link Callable} with {@link BouncyCastleKeyFactoryProvider} enabled temporarily.
 */
public static synchronized <T>T call(Callable<T> task) throws Exception {
  boolean needToAdd=true;
  for (  Provider provider : Security.getProviders()) {
    if (provider instanceof BouncyCastleKeyFactoryProvider) {
      needToAdd=false;
      break;
    }
  }
  if (needToAdd) {
    Security.addProvider(new BouncyCastleKeyFactoryProvider());
    try {
      return task.call();
    }
  finally {
      Security.removeProvider(PROVIDER_NAME);
    }
  }
 else {
    return task.call();
  }
}
