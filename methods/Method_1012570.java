public int find(E e){
  for (int i=0; i < size; i++) {
    if (data[i].equals(e))     return i;
  }
  return -1;
}
