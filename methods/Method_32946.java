@Override public boolean onKeyPreIme(int keyCode,KeyEvent event){
  if (LOG_KEY_EVENTS)   Log.i(EmulatorDebug.LOG_TAG,"onKeyPreIme(keyCode=" + keyCode + ", event=" + event + ")");
  if (keyCode == KeyEvent.KEYCODE_BACK) {
    if (mIsSelectingText) {
      toggleSelectingText(null);
      return true;
    }
 else     if (mClient.shouldBackButtonBeMappedToEscape()) {
switch (event.getAction()) {
case KeyEvent.ACTION_DOWN:
        return onKeyDown(keyCode,event);
case KeyEvent.ACTION_UP:
      return onKeyUp(keyCode,event);
  }
}
}
return super.onKeyPreIme(keyCode,event);
}
