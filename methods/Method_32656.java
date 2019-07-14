/** 
 * Gets the index of the field in this period.
 * @param type  the type to check, may be null which returns -1
 * @return the index of -1 if not supported
 */
public int indexOf(DurationFieldType type){
  for (int i=0, isize=size(); i < isize; i++) {
    if (iTypes[i] == type) {
      return i;
    }
  }
  return -1;
}
