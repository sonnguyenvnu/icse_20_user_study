/** 
 * @param key to be hashed
 * @return an index into the hash table This hash function is taken from the internals of Austin Appleby's MurmurHash3 algorithm. It is also used by the Trove for Java libraries.
 */
static long hash(long key){
  key^=key >>> 33;
  key*=0xff51afd7ed558ccdL;
  key^=key >>> 33;
  key*=0xc4ceb9fe1a85ec53L;
  key^=key >>> 33;
  return key;
}
