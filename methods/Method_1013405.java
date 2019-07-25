/** 
 * Extend the fingerprint <code>fp</code> by the bytes in the array <code>bytes</code>. 
 */
public static long Extend(long fp,byte[] bytes,int start,int len){
  final long[] mod=ByteModTable_7;
  int end=start + len;
  for (int i=start; i < end; i++) {
    fp=(fp >>> 8) ^ mod[(bytes[i] ^ (int)fp) & 0xFF];
  }
  return fp;
}
