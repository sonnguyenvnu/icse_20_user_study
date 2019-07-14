public static StaticBuffer positiveBuffer(final long value){
  WriteBuffer buffer=new WriteByteBuffer(positiveLength(value));
  writePositive(buffer,value);
  return buffer.getStaticBuffer();
}
