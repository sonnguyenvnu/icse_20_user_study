@Override public void onFocusChange(View view,boolean b){
  if (mEventHandler != null) {
    dispatchOnFocusChanged(mEventHandler,view,b);
  }
}
