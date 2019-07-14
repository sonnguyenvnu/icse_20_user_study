/** 
 * 5.0?? ??????????
 */
public void mUploadMessage(Intent intent,int resultCode){
  if (null == mUploadMessage)   return;
  Uri result=intent == null || resultCode != RESULT_OK ? null : intent.getData();
  mUploadMessage.onReceiveValue(result);
  mUploadMessage=null;
}
