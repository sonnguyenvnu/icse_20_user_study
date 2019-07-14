/** 
 * 5.0?? ??????????
 */
public void mUploadMessageForAndroid5(Intent intent,int resultCode){
  if (null == mUploadMessageForAndroid5)   return;
  Uri result=(intent == null || resultCode != RESULT_OK) ? null : intent.getData();
  if (result != null) {
    mUploadMessageForAndroid5.onReceiveValue(new Uri[]{result});
  }
 else {
    mUploadMessageForAndroid5.onReceiveValue(new Uri[]{});
  }
  mUploadMessageForAndroid5=null;
}
