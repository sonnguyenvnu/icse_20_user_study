@Override public final HttpResponseSetting proxy(final ProxyConfig proxyConfig,final Failover failover){
  ProxyConfig config=checkNotNull(proxyConfig,"Proxy config should not be null");
  this.request(InternalApis.context(config.localBase())).response(Moco.proxy(config,checkNotNull(failover,"Failover should not be null")));
  return this;
}
