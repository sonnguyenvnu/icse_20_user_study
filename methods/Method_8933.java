public void openKeyboard(){
  int currentSelection;
  try {
    currentSelection=messageEditText.getSelectionStart();
  }
 catch (  Exception e) {
    currentSelection=messageEditText.length();
    FileLog.e(e);
  }
  MotionEvent event=MotionEvent.obtain(0,0,MotionEvent.ACTION_DOWN,0,0,0);
  messageEditText.onTouchEvent(event);
  event.recycle();
  event=MotionEvent.obtain(0,0,MotionEvent.ACTION_UP,0,0,0);
  messageEditText.onTouchEvent(event);
  event.recycle();
  AndroidUtilities.showKeyboard(messageEditText);
  try {
    messageEditText.setSelection(currentSelection);
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
}
