public int maxIndex(){
  if (count == 0) {
    return -1;
  }
  int index=0;
  int value=values[0];
  for (int i=1; i < count; i++) {
    if (values[i] > value) {
      index=i;
      value=values[i];
    }
  }
  return index;
}
