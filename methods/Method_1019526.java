/** 
 * Creates a SingleItemSketch on the heap given a Memory. Checks the seed hash of the given Memory against a hash of the given seed.
 * @param mem the Memory to be heapified
 * @param seed a given hash seed
 * @return a SingleItemSketch
 */
public static SingleItemSketch heapify(final Memory mem,final long seed){
  final long memPre0=mem.getLong(0);
  checkDefaultBytes0to5(memPre0);
  final short seedHashIn=mem.getShort(6);
  final short seedHashCk=computeSeedHash(seed);
  checkSeedHashes(seedHashIn,seedHashCk);
  return new SingleItemSketch(mem.getLong(8),seed);
}
