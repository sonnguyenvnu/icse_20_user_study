/** 
 * Is this partial earlier than the specified partial. <p> The fields are compared in order, from largest to smallest. The first field that is non-equal is used to determine the result. <p> You may not pass null into this method. This is because you need a time zone to accurately determine the current date.
 * @param partial  a partial to check against, must not be null
 * @return true if this date is strictly before the date passed in
 * @throws IllegalArgumentException if the specified partial is null
 * @throws ClassCastException if the partial has field types that don't match
 * @since 1.1
 */
public boolean isBefore(ReadablePartial partial){
  if (partial == null) {
    throw new IllegalArgumentException("Partial cannot be null");
  }
  return compareTo(partial) < 0;
}
