protected void callOnColorChangedListeners(int oldColor,int newColor){
  if (colorChangedListeners != null && oldColor != newColor) {
    for (    OnColorChangedListener listener : colorChangedListeners) {
      try {
        listener.onColorChanged(newColor);
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
    }
  }
}
