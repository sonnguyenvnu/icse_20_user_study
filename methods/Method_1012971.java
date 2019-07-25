/** 
 * Computes the starting index of a given segment.
 * @param segment the segment of a big array.
 * @return the starting index of the segment.
 */
public static long start(final int segment){
  return (long)segment << SEGMENT_SHIFT;
}
