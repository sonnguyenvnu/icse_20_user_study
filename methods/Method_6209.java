public boolean isPadding(){
  for (int i=0; i < frameId.length(); i++) {
    if (frameId.charAt(0) != 0) {
      return false;
    }
  }
  return bodySize == 0;
}
