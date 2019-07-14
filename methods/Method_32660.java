/** 
 * Adds to the indexed field part of the period.
 * @param period  the period to query
 * @param index  the index to use
 * @param values  the array to populate
 * @param valueToAdd  the value to add
 * @return true if the array is updated
 * @throws UnsupportedOperationException if not supported
 */
boolean addIndexedField(ReadablePeriod period,int index,int[] values,int valueToAdd){
  if (valueToAdd == 0) {
    return false;
  }
  int realIndex=iIndices[index];
  if (realIndex == -1) {
    throw new UnsupportedOperationException("Field is not supported");
  }
  values[realIndex]=FieldUtils.safeAdd(values[realIndex],valueToAdd);
  return true;
}
