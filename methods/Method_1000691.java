public long first(){
  if (size() == 0)   return -1;
  return innerGet(offset);
}
