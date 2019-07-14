@Override public boolean isEOF(){
  return bufLength == -1 || bp == buf.length || ch == EOI && bp + 1 == buf.length;
}
