public void onFinish(){
  if (isChanged()) {
    ConfirmDiscardContentDialogFragment.show(this);
  }
 else {
    finish();
  }
}
