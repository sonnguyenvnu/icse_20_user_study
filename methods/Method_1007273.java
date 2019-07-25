public int read(int offset){
  if (offset < 0 || size <= offset)   throw new ArrayIndexOutOfBoundsException(offset);
  return buffer[offset];
}
