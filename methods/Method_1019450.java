/** 
 * Return the given Memory as a CpcSketch on the Java heap.
 * @param mem the given Memory
 * @param seed the seed used to create the original sketch from which the Memory was derived.
 * @return the given Memory as a CpcSketch on the Java heap.
 */
public static CpcSketch heapify(final Memory mem,final long seed){
  final CompressedState state=CompressedState.importFromMemory(mem);
  return uncompress(state,seed);
}
