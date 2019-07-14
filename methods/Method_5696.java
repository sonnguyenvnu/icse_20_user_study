/** 
 * Compares two format values for order. A known value is considered greater than  {@link Format#NO_VALUE}.
 * @param first The first value.
 * @param second The second value.
 * @return A negative integer if the first value is less than the second. Zero if they are equal.A positive integer if the first value is greater than the second.
 */
private static int compareFormatValues(int first,int second){
  return first == Format.NO_VALUE ? (second == Format.NO_VALUE ? 0 : -1) : (second == Format.NO_VALUE ? 1 : (first - second));
}
