@Override public int length(){
  int size=0;
  for (int i=0; i < inUse; i++) {
    size+=pool.get(i).length();
  }
  return size;
}
