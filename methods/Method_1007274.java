public void write(int offset,int value){
  if (offset < 0 || size <= offset)   throw new ArrayIndexOutOfBoundsException(offset);
  buffer[offset]=(byte)value;
}
