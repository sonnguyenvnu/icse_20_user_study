public void onFinish(){
  boolean isEmpty=TextUtils.isEmpty(mTextEdit.getText()) && mImageUris.isEmpty() && mLinkInfo == null;
  if (mChanged && !isEmpty) {
    ConfirmDiscardContentDialogFragment.show(this);
  }
 else {
    finish();
  }
}
