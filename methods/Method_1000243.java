@Override public Integer next(){
  if (curr < size) {
    return result.get(curr++);
  }
  return -1;
}
