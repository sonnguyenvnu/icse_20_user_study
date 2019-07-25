/** 
 * Instantiates an on-heap UpdateSketch from Memory.
 * @param srcMem <a href="{@docRoot}/resources/dictionary.html#mem">See Memory</a>
 * @param seed <a href="{@docRoot}/resources/dictionary.html#seed">See Update Hash Seed</a>.
 * @return an UpdateSketch
 */
public static UpdateSketch heapify(final Memory srcMem,final long seed){
  final Family family=Family.idToFamily(srcMem.getByte(FAMILY_BYTE));
  if (family.equals(Family.ALPHA)) {
    return HeapAlphaSketch.heapifyInstance(srcMem,seed);
  }
  return HeapQuickSelectSketch.heapifyInstance(srcMem,seed);
}
