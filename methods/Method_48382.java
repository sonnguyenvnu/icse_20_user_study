public static StaticBuffer padBuffer(StaticBuffer b,int length){
  if (b.length() >= length)   return b;
  byte[] data=new byte[length];
  for (int i=0; i < b.length(); i++) {
    data[i]=b.getByte(i);
  }
  return new StaticArrayBuffer(data);
}
