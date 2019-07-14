public void onFinish(){
  if (mCommentEdit.getText().length() > 0) {
    ConfirmDiscardContentDialogFragment.show(this);
  }
 else {
    finishAfterTransition();
  }
}
