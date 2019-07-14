@Override public boolean isEOF(){
  return bp == len || ch == EOI && bp + 1 == len;
}
