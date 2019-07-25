public static final int DWORD(final byte[] b,final int offset){
  if (offset + 3 >= b.length)   return 0;
  int ret=(b[offset + 3] & 0xff);
  ret=(ret << 8) | (b[offset + 2] & 0xff);
  ret=(ret << 8) | (b[offset + 1] & 0xff);
  ret=(ret << 8) | (b[offset] & 0xff);
  return ret;
}
