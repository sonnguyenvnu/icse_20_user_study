public static final int WORD(final byte[] b,final int offset){
  final int ret=((b[offset + 1] & 0xff) << 8) | (b[offset] & 0xff);
  return ret;
}
