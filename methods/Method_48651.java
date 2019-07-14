@Override public byte[] read(ScanBuffer buffer){
  int length=getLength(buffer);
  if (length < 0)   return null;
  return buffer.getBytes(length);
}
