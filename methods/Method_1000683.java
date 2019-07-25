public T first(){
  if (size() == 0)   return null;
  return innerGet(offset);
}
