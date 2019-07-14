public final ResponseHandler asResponseHandler(){
  Failover failover=getFailover();
  if (hasProxyConfig()) {
    return Moco.proxy(getProxyConfig(),failover);
  }
  return Moco.proxy(url.asResource(),failover);
}
