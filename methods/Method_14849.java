private void saveAndExit(boolean isChangePassword){
  if (isToConfirm && inputedString.equals(password) == false) {
    showShortToast(R.string.password_not_equal);
    return;
  }
  setResult(RESULT_OK,new Intent().putExtra(RESULT_PASSWORD,inputedString).putExtra(RESULT_IS_CHANGE_PASSWORD,isChangePassword));
  finish();
}
