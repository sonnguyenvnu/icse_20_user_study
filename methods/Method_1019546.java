/** 
 * Wrap the given WritableMemory and seed as a ArrayOfDoublesUpdatableSketch
 * @param mem the given Memory
 * @param seed the given seed
 * @return an ArrayOfDoublesUpdatableSketch
 */
public static ArrayOfDoublesUpdatableSketch wrap(final WritableMemory mem,final long seed){
  return new DirectArrayOfDoublesQuickSelectSketch(mem,seed);
}
