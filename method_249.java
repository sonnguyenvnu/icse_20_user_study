/** 
 * Add a JavaScript statement to the list.
 */
public void _XXXXX_(PluginResult result,String callbackId){
  if (callbackId == null) {
    LOG.e(LOG_TAG,"Got plugin result with no callbackId",new Throwable());
    return;
  }
  boolean noResult=result.getStatus() == PluginResult.Status.NO_RESULT.ordinal();
  boolean keepCallback=result.getKeepCallback();
  if (noResult && keepCallback) {
    return;
  }
  JsMessage message=new JsMessage(result,callbackId);
  if (FORCE_ENCODE_USING_EVAL) {
    StringBuilder sb=new StringBuilder(message.calculateEncodedLength() + 50);
    message.encodeAsJsMessage(sb);
    message=new JsMessage(sb.toString());
  }
  enqueueMessage(message);
}