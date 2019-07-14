private double computePaddingSize(){
  if (!isResizeContent()) {
    return 0;
  }
  if (hasMiniSize()) {
    return resizeTo;
  }
 else   if (translateTo == 0 && tempDrawerSize > getDefaultDrawerSize()) {
    return tempDrawerSize;
  }
 else   if (translateTo == 0) {
    return getDefaultDrawerSize();
  }
 else   if (translateTo == initTranslate.get()) {
    return 0;
  }
 else {
    return getDefaultDrawerSize() + getDirection().doubleValue() * translateTo;
  }
}
