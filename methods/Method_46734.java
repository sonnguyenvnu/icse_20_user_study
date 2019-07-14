/** 
 * ??????????
 * @param key key
 * @return RpcContent
 */
public synchronized RpcContent addKey(String key){
  RpcContent rpcContent=createRpcContent();
  map.put(key,rpcContent);
  return rpcContent;
}
