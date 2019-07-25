public boolean contains(int e){
  for (int i=0; i < size; i++) {
    if (data[i] == e)     return true;
  }
  return false;
}
