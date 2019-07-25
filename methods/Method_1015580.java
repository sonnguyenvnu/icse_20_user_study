protected final int index(int idx){
  return idx & (buf.length - 1);
}
