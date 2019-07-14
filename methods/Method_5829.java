/** 
 * Throws  {@link IndexOutOfBoundsException} if {@code index} falls outside the specified bounds.
 * @param index The index to test.
 * @param start The start of the allowed range (inclusive).
 * @param limit The end of the allowed range (exclusive).
 * @return The {@code index} that was validated.
 * @throws IndexOutOfBoundsException If {@code index} falls outside the specified bounds.
 */
public static int checkIndex(int index,int start,int limit){
  if (index < start || index >= limit) {
    throw new IndexOutOfBoundsException();
  }
  return index;
}
