/** 
 * Wrap the given WritableMemory as an ArrayOfDoublesUpdatableSketch
 * @param mem the given Memory
 * @return an ArrayOfDoublesUpdatableSketch
 */
public static ArrayOfDoublesUpdatableSketch wrap(final WritableMemory mem){
  return wrap(mem,DEFAULT_UPDATE_SEED);
}
