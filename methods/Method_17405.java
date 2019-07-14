/** 
 * Sets the membership flag for the computed bit location.
 * @param item the element's hash
 * @param seedIndex the hash seed index
 * @return if the membership changed as a result of this operation
 */
@SuppressWarnings("PMD.LinguisticNaming") boolean setAt(int item,int seedIndex){
  int hash=seeded(item,seedIndex);
  int index=hash >>> tableShift;
  long previous=table[index];
  table[index]|=bitmask(hash);
  return (table[index] != previous);
}
