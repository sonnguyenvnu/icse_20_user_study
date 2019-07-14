public void onFinish(){
  if (mTextEdit.getText().length() > 0) {
    ConfirmDiscardContentDialogFragment.show(this);
  }
 else {
    finish();
  }
}
