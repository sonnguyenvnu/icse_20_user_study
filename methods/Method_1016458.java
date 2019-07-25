public static final int BYTE(final byte[] b,final int offset){
  final int ret=(b[offset] & 0xff);
  return ret;
}
