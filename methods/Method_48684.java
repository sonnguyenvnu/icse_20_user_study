@Override public String readByteOrder(ScanBuffer buffer){
  byte prefix=buffer.getByte();
  if (prefix == -1)   return null;
  assert prefix == 0;
  StringBuilder s=new StringBuilder();
  while (true) {
    char c=cs.readByteOrder(buffer);
    if (((int)c) > 0)     s.append(c);
 else     break;
  }
  return s.toString();
}
