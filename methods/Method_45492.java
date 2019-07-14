/** 
 * ????????
 * @param key      ???
 * @param listener ?????
 * @see RpcOptions
 */
public static synchronized void unSubscribe(String key,RpcConfigListener listener){
  List<RpcConfigListener> listeners=CFG_LISTENER.get(key);
  if (listeners != null) {
    listeners.remove(listener);
    if (listeners.size() == 0) {
      CFG_LISTENER.remove(key);
    }
  }
}
