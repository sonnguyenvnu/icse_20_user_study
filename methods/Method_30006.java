public void onFinish(){
  CharSequence text=mTextEdit.getText();
  if (text.length() > 0 && !TextUtils.equals(text,mText)) {
    ConfirmDiscardContentDialogFragment.show(this);
  }
 else {
    finish();
  }
}
