/** 
 * ?????????
 */
private void register(){
  SystemUtil.hideKeyBoard(getContext());
  final String password=mPasswordEdit.getText().toString().trim();
  if (TextUtils.isEmpty(password)) {
    ToastUtil.showToast(R.string.toast_error_empty_password);
    return;
  }
  final String passwordAgain=mPasswordAgainEdit.getText().toString().trim();
  if (TextUtils.isEmpty(passwordAgain)) {
    ToastUtil.showToast(R.string.toast_error_empty_password_confirm);
    return;
  }
  if (!password.equals(passwordAgain)) {
    ToastUtil.showToast(R.string.toast_error_password_not_consistent);
    return;
  }
  showLoading(R.string.label_being_something);
  mRegisterBtn.setEnabled(false);
  getCallbacks().createUser(mMobile,password);
}
