/** 
 * Returns the remainder from the modulo division of the 128-bit output of the murmurHash3 by the divisor.
 * @param h0 The lower 64-bits of the 128-bit MurmurHash3 hash.
 * @param h1 The upper 64-bits of the 128-bit MurmurHash3 hash.
 * @param divisor Must be positive and greater than zero.
 * @return the modulo result.
 */
public static int modulo(final long h0,final long h1,final int divisor){
  final long d=divisor;
  final long modH0=(h0 < 0L) ? addRule(mulRule(BIT62,2L,d),(h0 & MAX_LONG),d) : h0 % d;
  final long modH1=(h1 < 0L) ? addRule(mulRule(BIT62,2L,d),(h1 & MAX_LONG),d) : h1 % d;
  final long modTop=mulRule(mulRule(BIT62,4L,d),modH1,d);
  return (int)addRule(modTop,modH0,d);
}
