private void onWindowSizeChanged(){
  int size=sizeNotifierLayout.getHeight();
  if (!keyboardVisible) {
    size-=emojiPadding;
  }
  if (delegate != null) {
    delegate.onWindowSizeChanged(size);
  }
}
