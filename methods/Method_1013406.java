/** 
 * Extend the fingerprint <code>fp</code> by the bytes of the stream <code>is</code>. <code>IOException</code> is thrown in the event of an error reading the stream. 
 */
public static long Extend(long fp,InputStream is) throws IOException {
  final long[] mod=ByteModTable_7;
  final int mask=0xFF;
  int i;
  while ((i=is.read()) != -1) {
    fp=((fp >>> 8) ^ (mod[(i ^ ((int)fp)) & mask]));
  }
  return fp;
}
