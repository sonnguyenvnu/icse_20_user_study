@Override public char charAt(int index){
  if (index < length)   return value;
  throw new IndexOutOfBoundsException();
}
