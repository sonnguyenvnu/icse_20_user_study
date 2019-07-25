/** 
 * Wrap the given WritableMemory as an ArrayOfDoublesUnion
 * @param mem the given Memory
 * @return an ArrayOfDoublesUnion
 */
public static ArrayOfDoublesUnion wrap(final WritableMemory mem){
  return wrap(mem,DEFAULT_UPDATE_SEED);
}
