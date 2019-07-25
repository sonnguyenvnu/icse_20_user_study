private byte[] reverse(byte[] bytes){
  for (int i=0, length=bytes.length >> 1; i < length; i++) {
    int j=bytes.length - 1 - i;
    byte t=bytes[i];
    bytes[i]=bytes[j];
    bytes[j]=t;
  }
  return bytes;
}
