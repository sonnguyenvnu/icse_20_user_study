/** 
 * Build ProviderInfoListener for consumer bootstrap.
 * @param bootstrap ConsumerBootstrap
 * @return ProviderInfoListener
 */
protected ProviderInfoListener buildProviderInfoListener(ConsumerBootstrap bootstrap){
  return new ClusterProviderInfoListener(bootstrap.getCluster());
}
