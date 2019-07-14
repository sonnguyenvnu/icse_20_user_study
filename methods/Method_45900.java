@Override protected ClientProxyInvoker buildClientProxyInvoker(ConsumerBootstrap bootstrap){
  return new BoltClientProxyInvoker(bootstrap);
}
