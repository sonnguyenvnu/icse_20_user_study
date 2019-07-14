public static short getShort(final byte[] b,final int off){
  return (short)(((b[off + 1] & 0xFF)) + ((b[off]) << 8));
}
