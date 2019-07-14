/** 
 * Gets the index of the first fields to have the specified duration, or -1 if the field is unsupported.
 * @param type  the type to check, may be null which returns -1
 * @return the index of the field, -1 if unsupported
 */
protected int indexOf(DurationFieldType type){
  for (int i=0, isize=size(); i < isize; i++) {
    if (getFieldType(i).getDurationType() == type) {
      return i;
    }
  }
  return -1;
}
