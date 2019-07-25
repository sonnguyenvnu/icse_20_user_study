/** 
 * Returns a list of integers from the given <code>from</code> value (inclusive) to the given <code>to</code> value (exclusive).
 * @param from The minimum value for the list (inclusive).
 * @param to   The maximum value for the list (exclusive).
 * @return A list of integers from the given <code>from</code> value (inclusive) to the given<code>to</code> value (exclusive).
 */
public static List<Integer> range(final int from,final int to){
  final Buffer<Integer> buf=empty();
  for (int i=from; i < to; i++) {
    buf.snoc(i);
  }
  return buf.toList();
}
