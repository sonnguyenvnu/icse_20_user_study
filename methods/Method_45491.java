/** 
 * ??????
 * @param key      ???
 * @param listener ?????
 * @see RpcOptions
 */
public static synchronized void subscribe(String key,RpcConfigListener listener){
  List<RpcConfigListener> listeners=CFG_LISTENER.get(key);
  if (listeners == null) {
    listeners=new ArrayList<RpcConfigListener>();
    CFG_LISTENER.put(key,listeners);
  }
  listeners.add(listener);
}
