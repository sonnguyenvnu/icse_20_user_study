private void tryPartialAnimation(double direction){
  if (hasMiniSize()) {
    if (prefSizeProperty.get() > (getMiniDrawerSize() + getDefaultDrawerSize()) / 2 && prefSizeProperty.get() < getDefaultDrawerSize()) {
      partialOpen();
    }
 else     if (prefSizeProperty.get() <= (getMiniDrawerSize() + getDefaultDrawerSize()) / 2) {
      partialClose();
    }
 else     if (prefSizeProperty.get() >= getDefaultDrawerSize()) {
      resizeTo=getDefaultDrawerSize();
      overlayPane.setMouseTransparent(!isOverLayVisible());
    }
  }
 else {
    if (direction * translateProperty.get() > direction * initTranslate.get() / 2) {
      if (translateProperty.get() != 0.0) {
        partialOpen();
      }
    }
 else     if (translateProperty.get() != initTranslate.get()) {
      partialClose();
    }
  }
}
