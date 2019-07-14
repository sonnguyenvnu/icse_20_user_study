private void setShowOriginal(boolean value){
  if (showOriginal == value) {
    return;
  }
  showOriginal=value;
  if (eglThread != null) {
    eglThread.requestRender(false);
  }
}
