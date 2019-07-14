@Override public char[] read(ScanBuffer buffer){
  int length=getLength(buffer);
  if (length < 0)   return null;
  return buffer.getChars(length);
}
