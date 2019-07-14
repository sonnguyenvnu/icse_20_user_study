private void next(){
  if (isToSetting == false || isToConfirm) {
    saveAndExit();
  }
 else {
    startActivityForResult(NumberPasswordActivity.createIntent(context,inputedString,"????????",""),REQUEST_TO_PASSWORD_SETTING);
    overridePendingTransition(R.anim.bottom_push_in,R.anim.hold);
  }
}
