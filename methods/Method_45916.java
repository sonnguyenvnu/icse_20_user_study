@Override protected ClientProxyInvoker buildClientProxyInvoker(ConsumerBootstrap bootstrap){
  return new RestClientProxyInvoker(bootstrap);
}
