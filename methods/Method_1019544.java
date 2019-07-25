/** 
 * Heapify the given Memory and seed as a ArrayOfDoublesUpdatableSketch
 * @param mem the given Memory
 * @param seed the given seed
 * @return an ArrayOfDoublesUpdatableSketch
 */
public static ArrayOfDoublesUpdatableSketch heapify(final Memory mem,final long seed){
  return new HeapArrayOfDoublesQuickSelectSketch(mem,seed);
}
