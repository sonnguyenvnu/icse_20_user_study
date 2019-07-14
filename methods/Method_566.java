public final char[] sub_chars(int offset,int count){
  if (count < 0) {
    throw new StringIndexOutOfBoundsException(count);
  }
  if (offset == 0) {
    return buf;
  }
  char[] chars=new char[count];
  System.arraycopy(buf,offset,chars,0,count);
  return chars;
}
