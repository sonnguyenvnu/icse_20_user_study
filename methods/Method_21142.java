private void requestFocusAndOpenKeyboard(){
  this.messageEditText.requestFocus();
  this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
}
