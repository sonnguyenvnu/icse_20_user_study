/** 
 * Returns a Heap Union object that has been initialized with the data from the given memory image of a sketch.
 * @param srcMem A memory image of a DoublesSketch to be used as a source of data,but will not be modified.
 * @return a Union object
 * @deprecated moved to DoublesUnion
 */
@Deprecated public static DoublesUnion heapify(final Memory srcMem){
  return DoublesUnionImpl.heapifyInstance(srcMem);
}
