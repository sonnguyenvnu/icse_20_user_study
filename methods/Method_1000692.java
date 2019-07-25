public long last(){
  if (size() == 0)   return -1;
  return innerGet(cursor - 1);
}
