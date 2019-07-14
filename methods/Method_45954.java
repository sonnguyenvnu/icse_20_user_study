/** 
 * Build ClientProxyInvoker for consumer bootstrap.
 * @param bootstrap ConsumerBootstrap
 * @return ClientProxyInvoker
 */
protected ClientProxyInvoker buildClientProxyInvoker(ConsumerBootstrap bootstrap){
  return new DefaultClientProxyInvoker(bootstrap);
}
