private void updateTempDrawerSize(){
  if (sizeProperty.get() > getDefaultDrawerSize()) {
    tempDrawerSize=prefSizeProperty.get();
  }
 else {
    tempDrawerSize=getDefaultDrawerSize();
  }
}
