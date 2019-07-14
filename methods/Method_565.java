public final String subString(int offset,int count){
  if (count < 0) {
    throw new StringIndexOutOfBoundsException(count);
  }
  return new String(buf,offset,count);
}
