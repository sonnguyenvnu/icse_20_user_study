/** 
 * Hash a long into 64 bits instead of the normal 32. This uses a xor shift implementation to attempt psuedo randomness so object ids have an even spread for less chance of collisions. <p> From http://stackoverflow.com/a/11554034 <p> http://www.javamex.com/tutorials/random_numbers/xorshift.shtml
 */
public static long hashLong64Bit(long value){
  value^=(value << 21);
  value^=(value >>> 35);
  value^=(value << 4);
  return value;
}
