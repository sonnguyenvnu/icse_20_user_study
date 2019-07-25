protected final int increment(int index){
  return index + 1 == buf.length ? 0 : index + 1;
}
