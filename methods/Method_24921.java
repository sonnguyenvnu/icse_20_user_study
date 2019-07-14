public boolean isGrayScale(){
  if (handles.size() <= 2) {
    int value=handles.get(0).newValue.intValue();
    if ((value & 0xff000000) == 0) {
      return true;
    }
  }
  return false;
}
