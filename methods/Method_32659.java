/** 
 * Sets the indexed field part of the period.
 * @param period  the period to query
 * @param index  the index to use
 * @param values  the array to populate
 * @param newValue  the value to set
 * @throws UnsupportedOperationException if not supported
 */
boolean setIndexedField(ReadablePeriod period,int index,int[] values,int newValue){
  int realIndex=iIndices[index];
  if (realIndex == -1) {
    throw new UnsupportedOperationException("Field is not supported");
  }
  values[realIndex]=newValue;
  return true;
}
