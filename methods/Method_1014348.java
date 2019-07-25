@Override public Version value(ByteBuffer bytes){
  long value=bytes.getInt() & 0xFFFFFFFFL;
  long major=(value >> 16) & 0xFFL;
  long minor=value & 0xFFL;
  return new Version(major,minor);
}
