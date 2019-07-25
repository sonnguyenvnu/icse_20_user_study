/** 
 * Returns a Heap Union object that has been initialized with the data from the given sketch.
 * @param sketch A DoublesSketch to be used as a source of data only and will not be modified.
 * @return a DoublesUnion object
 * @deprecated moved to DoublesUnion
 */
@Deprecated public static DoublesUnion heapify(final DoublesSketch sketch){
  return DoublesUnionImpl.heapifyInstance(sketch);
}
