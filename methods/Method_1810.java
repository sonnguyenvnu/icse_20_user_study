/** 
 * Checks whether a provided range is within this one.
 * @return true if the provided range is within this one, false if given null
 */
public boolean contains(@Nullable BytesRange compare){
  if (compare == null) {
    return false;
  }
  return from <= compare.from && to >= compare.to;
}
