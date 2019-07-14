/** 
 * Register the implementation of the  {@link HystrixNetworkAuditorEventListener} that will receive the events.
 * @param bridge {@link HystrixNetworkAuditorEventListener} implementation
 */
public static void registerEventListener(HystrixNetworkAuditorEventListener bridge){
  if (bridge == null) {
    throw new IllegalArgumentException("HystrixNetworkAuditorEventListener can not be NULL");
  }
  HystrixNetworkAuditorAgent.bridge=bridge;
}
