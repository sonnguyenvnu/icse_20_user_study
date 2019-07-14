@Override public void onFocusChange(View v,boolean hasFocus){
  if (!hasFocus) {
    int state=doValidate(false);
    mButton.setEnabled(state != ReturnState.STATE_ERROR);
  }
}
