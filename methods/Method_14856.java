/** 
 * ???
 */
private void toNextStep(){
  if (type != TYPE_VERIFY) {
    if (EditTextUtil.isInputedCorrect(context,etPasswordPassword0,EditTextUtil.TYPE_PASSWORD) == false || EditTextUtil.isInputedCorrect(context,etPasswordPassword1,EditTextUtil.TYPE_PASSWORD) == false) {
      return;
    }
    final String password0=StringUtil.getTrimedString(etPasswordPassword0);
    String password1=StringUtil.getTrimedString(etPasswordPassword1);
    if (!password0.equals(password1)) {
      showShortToast("?????????????");
      return;
    }
  }
switch (type) {
case TYPE_REGISTER:
    register();
  break;
case TYPE_VERIFY:
checkVerify(Verify.TYPE_LOGIN,true);
break;
case TYPE_RESET:
checkVerify(Verify.TYPE_PASSWORD,true);
break;
}
}
