/** 
 * Hash a string into 64 bits instead of the normal 32. This allows us to better use strings as a model id with less chance of collisions. This uses the FNV-1a algorithm for a good mix of speed and distribution. <p> Performance comparisons found at http://stackoverflow.com/a/1660613 <p> Hash implementation from http://www.isthe.com/chongo/tech/comp/fnv/index.html#FNV-1a
 */
public static long hashString64Bit(@Nullable CharSequence str){
  if (str == null) {
    return 0;
  }
  long result=0xcbf29ce484222325L;
  final int len=str.length();
  for (int i=0; i < len; i++) {
    result^=str.charAt(i);
    result*=0x100000001b3L;
  }
  return result;
}
