/** 
 * Calculate the hash of a key. The result depends on the key, the recursion level, and the offset.
 * @param x the key
 * @param level the recursion level
 * @param offset the index of the hash function
 * @param size the size of the bucket
 * @return the hash (a value between 0, including, and the size, excluding)
 */
private static int hash(int x,int level,int offset,int size){
  x+=level * OFFSETS + offset;
  x=((x >>> 16) ^ x) * 0x45d9f3b;
  x=((x >>> 16) ^ x) * 0x45d9f3b;
  x=(x >>> 16) ^ x;
  return Math.abs(x % size);
}
