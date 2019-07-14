/** 
 * ?????
 * @param type
 * @param fromServer
 */
private boolean checkVerify(int type,boolean fromServer){
  if (EditTextUtil.isInputedCorrect(context,etPasswordPhone,EditTextUtil.TYPE_PHONE) == false || EditTextUtil.isInputedCorrect(context,etPasswordVerify,EditTextUtil.TYPE_VERIFY) == false) {
    return false;
  }
  if (fromServer) {
    showProgressDialog();
    HttpRequest.checkVerify(type,StringUtil.getTrimedString(etPasswordPhone),StringUtil.getTrimedString(etPasswordVerify),HTTP_CHECK_VERIFY,this);
  }
  return true;
}
