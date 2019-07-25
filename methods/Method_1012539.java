public int find(int e){
  for (int i=0; i < size; i++) {
    if (data[i] == e)     return i;
  }
  return -1;
}
