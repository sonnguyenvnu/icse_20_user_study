public void notifyHeightChanged(){
  if (delegate != null) {
    keyboardHeight=getKeyboardHeight();
    final boolean isWidthGreater=AndroidUtilities.displaySize.x > AndroidUtilities.displaySize.y;
    post(new Runnable(){
      @Override public void run(){
        if (delegate != null) {
          delegate.onSizeChanged(keyboardHeight,isWidthGreater);
        }
      }
    }
);
  }
}
