static char[] encode(byte[] bytes){
  int nBytes=bytes.length;
  char[] result=new char[2 * nBytes];
  int j=0;
  for (int i=0; i < nBytes; i++) {
    result[j]=HEX[(0xF0 & bytes[i]) >>> 4];
    result[j + 1]=HEX[(0x0F & bytes[i])];
    j+=2;
  }
  return result;
}
