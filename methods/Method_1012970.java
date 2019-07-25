/** 
 * Computes the displacement associated with a given index.
 * @param index an index into a big array.
 * @return the associated displacement (in the associated{@linkplain #segment(long) segment}).
 */
public static int displacement(final long index){
  return (int)(index & SEGMENT_MASK);
}
