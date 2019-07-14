/** 
 * Remove value
 * @param key Key
 */
@JustForTest synchronized static void removeValue(String key){
  Object oldValue=CFG.get(key);
  if (oldValue != null) {
    CFG.remove(key);
    List<RpcConfigListener> rpcConfigListeners=CFG_LISTENER.get(key);
    if (CommonUtils.isNotEmpty(rpcConfigListeners)) {
      for (      RpcConfigListener rpcConfigListener : rpcConfigListeners) {
        rpcConfigListener.onChange(oldValue,null);
      }
    }
  }
}
