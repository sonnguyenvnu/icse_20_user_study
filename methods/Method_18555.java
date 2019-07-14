@Override public void setFocusable(boolean isFocusable){
  if (isFocusable) {
    mFocusState=FOCUS_SET_TRUE;
  }
 else {
    mFocusState=FOCUS_SET_FALSE;
  }
}
