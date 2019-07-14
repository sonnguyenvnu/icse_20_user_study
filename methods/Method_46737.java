public RpcContent getKey(String key){
  RpcContent rpcContent=map.get(key);
  clearKey(key);
  return rpcContent;
}
