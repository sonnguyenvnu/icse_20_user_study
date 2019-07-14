private void hideKeyboard(){
  toCommentItem=null;
  runUiThread(new Runnable(){
    @Override public void run(){
      etMomentInput.setHint("??");
      EditTextUtil.hideKeyboard(context,etMomentInput);
    }
  }
);
}
