/** 
 * Does this partial match the specified partial. <p> A match occurs when all the fields of this partial are the same as the corresponding fields on the specified partial.
 * @param partial  a partial to check against, must not be null
 * @return true if this partial matches the specified partial
 * @throws IllegalArgumentException if the partial is null
 * @throws IllegalArgumentException if the fields of the two partials do not match
 * @since 1.5
 */
public boolean isMatch(ReadablePartial partial){
  if (partial == null) {
    throw new IllegalArgumentException("The partial must not be null");
  }
  for (int i=0; i < iTypes.length; i++) {
    int value=partial.get(iTypes[i]);
    if (value != iValues[i]) {
      return false;
    }
  }
  return true;
}
