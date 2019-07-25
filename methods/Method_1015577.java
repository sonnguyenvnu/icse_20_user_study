protected final int index(int idx){
  return idx & (capacity - 1);
}
