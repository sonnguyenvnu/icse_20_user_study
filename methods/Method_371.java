private int getDimensions(){
  int i=1;
  while (buf[off + i] == '[') {
    ++i;
  }
  return i;
}
