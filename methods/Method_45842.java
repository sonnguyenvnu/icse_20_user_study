@Override public long getValue(){
  long ret=crc;
  return (~ret) & 0xffffffffL;
}
