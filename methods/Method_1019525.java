/** 
 * Creates a SingleItemSketch on the heap given a Memory and assumes the DEFAULT_UPDATE_SEED.
 * @param mem the Memory to be heapified.  It must be a least 16 bytes.
 * @return a SingleItemSketch
 */
public static SingleItemSketch heapify(final Memory mem){
  final long memPre0=mem.getLong(0);
  checkDefaultBytes0to7(memPre0);
  return new SingleItemSketch(mem.getLong(8));
}
