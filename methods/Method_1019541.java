/** 
 * Heapify the given Memory as an ArrayOfDoublesSketch
 * @param mem the given Memory
 * @return an ArrayOfDoublesSketch
 */
public static ArrayOfDoublesSketch heapify(final Memory mem){
  return heapify(mem,DEFAULT_UPDATE_SEED);
}
