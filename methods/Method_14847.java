/** 
 * @param verify
 */
private void toInput(String phone,String verify){
  this.phone=phone;
  this.verify=verify;
  if (isToSetting == false || isToConfirm == true) {
    return;
  }
  Log.i(TAG,"toInput phone = " + phone + "\n verify = " + verify);
  if (StringUtil.isNotEmpty(phone,true) == false || StringUtil.isNotEmpty(verify,true) == false) {
    toActivity(PasswordActivity.createIntent(context,PasswordActivity.TYPE_VERIFY).putExtra(INTENT_TITLE,"????"),REQUEST_TO_VERIFY_PHONE);
    return;
  }
  EditTextManager.showKeyboard(context,etPasswordSetting);
}
