public char[] getChars(int position,int length){
  char[] result=new char[length];
  for (int i=0; i < length; i++) {
    result[i]=getChar(position);
    position+=CHAR_LEN;
  }
  return result;
}
