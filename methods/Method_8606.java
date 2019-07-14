@Override public void onWindowFocusChanged(boolean hasWindowFocus){
  if (Build.VERSION.SDK_INT < 23 && !hasWindowFocus && copyPasteShowed) {
    return;
  }
  super.onWindowFocusChanged(hasWindowFocus);
}
