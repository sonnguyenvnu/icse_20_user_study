/** 
 * ??message ???????????
 * @param m Message
 */
void dispatchMessage(Message m){
  String messageJson=m.toJson();
  messageJson=messageJson.replaceAll("(\\\\)([^utrn])","\\\\\\\\$1$2");
  messageJson=messageJson.replaceAll("(?<=[^\\\\])(\")","\\\\\"");
  messageJson=messageJson.replaceAll("(?<=[^\\\\])(\')","\\\\\'");
  messageJson=messageJson.replaceAll("%7B",URLEncoder.encode("%7B"));
  messageJson=messageJson.replaceAll("%7D",URLEncoder.encode("%7D"));
  messageJson=messageJson.replaceAll("%22",URLEncoder.encode("%22"));
  String javascriptCommand=String.format(BridgeUtil.JS_HANDLE_MESSAGE_FROM_JAVA,messageJson);
  if (Thread.currentThread() == Looper.getMainLooper().getThread()) {
    this.loadUrl(javascriptCommand);
  }
}
