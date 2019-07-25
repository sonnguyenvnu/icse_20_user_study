/** 
 * Heapify takes the SetOperations image in Memory and instantiates an on-heap SetOperation using the <a href=" {@docRoot}/resources/dictionary.html#defaultUpdateSeed">Default Update Seed</a>. The resulting SetOperation will not retain any link to the source Memory.
 * @param srcMem an image of a SetOperation where the image seed hash matches the default seed hash.<a href=" {@docRoot}/resources/dictionary.html#mem">See Memory</a>
 * @return a Heap-based SetOperation from the given Memory
 */
public static SetOperation heapify(final Memory srcMem){
  return heapify(srcMem,DEFAULT_UPDATE_SEED);
}
