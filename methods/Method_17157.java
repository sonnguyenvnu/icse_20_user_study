/** 
 * Applies a supplemental hash function to a given hashCode, which defends against poor quality hash functions.
 */
int spread(int x){
  x=((x >>> 16) ^ x) * 0x45d9f3b;
  x=((x >>> 16) ^ x) * 0x45d9f3b;
  return (x >>> 16) ^ x;
}
