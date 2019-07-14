public static StaticBuffer positiveBuffer(long[] value){
  int len=0;
  for (  long aValue : value)   len+=positiveLength(aValue);
  WriteBuffer buffer=new WriteByteBuffer(len);
  for (  long aValue : value)   writePositive(buffer,aValue);
  return buffer.getStaticBuffer();
}
