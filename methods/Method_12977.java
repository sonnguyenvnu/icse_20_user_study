/** 
 * unregister handler
 * @param handlerName
 */
public void unregisterHandler(String handlerName){
  if (handlerName != null) {
    messageHandlers.remove(handlerName);
  }
}
