/** 
 * Validates an interval.
 * @param start  the start instant in milliseconds
 * @param end  the end instant in milliseconds
 * @throws IllegalArgumentException if the interval is invalid
 */
protected void checkInterval(long start,long end){
  if (end < start) {
    throw new IllegalArgumentException("The end instant must be greater than the start instant");
  }
}
