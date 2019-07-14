/** 
 * ??message?????
 * @param handlerName handlerName
 * @param data data
 * @param responseCallback CallBackFunction
 */
private void doSend(String handlerName,String data,CallBackFunction responseCallback){
  Message m=new Message();
  if (!TextUtils.isEmpty(data)) {
    m.setData(data);
  }
  if (responseCallback != null) {
    String callbackStr=String.format(BridgeUtil.CALLBACK_ID_FORMAT,++uniqueId + (BridgeUtil.UNDERLINE_STR + SystemClock.currentThreadTimeMillis()));
    responseCallbacks.put(callbackStr,responseCallback);
    m.setCallbackId(callbackStr);
  }
  if (!TextUtils.isEmpty(handlerName)) {
    m.setHandlerName(handlerName);
  }
  queueMessage(m);
}
