/** 
 * Gets a long from a byte buffer in little endian byte order.
 */
public static long getLongLittleEndian(final byte[] buf,final int offset){
  return ((long)buf[offset + 7] << 56) | ((buf[offset + 6] & 0xffL) << 48) | ((buf[offset + 5] & 0xffL) << 40) | ((buf[offset + 4] & 0xffL) << 32) | ((buf[offset + 3] & 0xffL) << 24) | ((buf[offset + 2] & 0xffL) << 16) | ((buf[offset + 1] & 0xffL) << 8) | ((buf[offset] & 0xffL));
}
