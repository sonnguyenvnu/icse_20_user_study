/** 
 * Gets the indexed field part of the period.
 * @param period  the period to query
 * @param index  the index to use
 * @return the value of the field, zero if unsupported
 */
int getIndexedField(ReadablePeriod period,int index){
  int realIndex=iIndices[index];
  return (realIndex == -1 ? 0 : period.getValue(realIndex));
}
