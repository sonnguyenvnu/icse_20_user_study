public char first(){
  if (size() == 0)   return (char)0;
  return innerGet(offset);
}
