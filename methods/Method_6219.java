boolean isXingFrame(){
  int xingOffset=header.getXingOffset();
  if (bytes.length < xingOffset + 12) {
    return false;
  }
  if (xingOffset < 0 || bytes.length < xingOffset + 8) {
    return false;
  }
  if (bytes[xingOffset] == 'X' && bytes[xingOffset + 1] == 'i' && bytes[xingOffset + 2] == 'n' && bytes[xingOffset + 3] == 'g') {
    return true;
  }
  if (bytes[xingOffset] == 'I' && bytes[xingOffset + 1] == 'n' && bytes[xingOffset + 2] == 'f' && bytes[xingOffset + 3] == 'o') {
    return true;
  }
  return false;
}
