private RpcContent createRpcContent(){
  if (cacheList.size() < cacheSize) {
    RpcContent rpcContent=new RpcContent(getWaitTime());
    rpcContent.init();
    cacheList.add(rpcContent);
    return rpcContent;
  }
 else {
    return findRpcContent();
  }
}
