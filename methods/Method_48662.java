@Override public float[] read(ScanBuffer buffer){
  int length=getLength(buffer);
  if (length < 0)   return null;
  return buffer.getFloats(length);
}
