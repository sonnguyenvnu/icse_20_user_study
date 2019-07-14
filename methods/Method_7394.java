public void setRemoteEndpoints(TLRPC.TL_phoneConnection[] endpoints,boolean allowP2p,boolean tcp,int connectionMaxLayer){
  if (endpoints.length == 0) {
    throw new IllegalArgumentException("endpoints size is 0");
  }
  for (int a=0; a < endpoints.length; a++) {
    TLRPC.TL_phoneConnection endpoint=endpoints[a];
    if (endpoint.ip == null || endpoint.ip.length() == 0) {
      throw new IllegalArgumentException("endpoint " + endpoint + " has empty/null ipv4");
    }
    if (endpoint.peer_tag != null && endpoint.peer_tag.length != 16) {
      throw new IllegalArgumentException("endpoint " + endpoint + " has peer_tag of wrong length");
    }
  }
  ensureNativeInstance();
  nativeSetRemoteEndpoints(nativeInst,endpoints,allowP2p,tcp,connectionMaxLayer);
}
