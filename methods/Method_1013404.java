/** 
 * Extend the fingerprint <code>fp</code> by the characters of  <code>chars</code>. 
 */
public static long Extend(long fp,char[] chars,int start,int len){
  final long[] mod=ByteModTable_7;
  int end=start + len;
  for (int i=start; i < end; i++) {
    fp=((fp >>> 8) ^ (mod[(((int)chars[i]) ^ ((int)fp)) & 0xFF]));
  }
  return fp;
}
