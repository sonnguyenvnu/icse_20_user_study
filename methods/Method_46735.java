/** 
 * ??????
 * @return RpcContent
 */
private RpcContent findRpcContent(){
synchronized (freeList) {
    RpcContent cacheContent=freeList.getFirst();
    if (!cacheContent.isUsed()) {
      cacheContent.init();
      freeList.remove(cacheContent);
      return cacheContent;
    }
  }
  RpcContent rpcContent=new RpcContent(getWaitTime());
  rpcContent.init();
  return rpcContent;
}
