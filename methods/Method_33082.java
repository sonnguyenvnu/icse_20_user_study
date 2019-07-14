public void setDefaultDrawerSize(double size){
  defaultSizeProperty.set(size);
  if (getMiniDrawerSize() < 0) {
    updateSize(size);
  }
}
