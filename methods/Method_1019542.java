/** 
 * Wrap the given Memory as an ArrayOfDoublesUnion
 * @param mem the given Memory
 * @return an ArrayOfDoublesUnion
 */
public static ArrayOfDoublesUnion wrap(final Memory mem){
  return wrap(mem,DEFAULT_UPDATE_SEED);
}
