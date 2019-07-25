/** 
 * Returns a new instance, which contains a subset of the elements specified by the given BitSet. Indexes in the BitSet with a zero are included, while indexes with a one are excluded. Mutability of the result is inherited from the original.
 * @param exclusionSet {@code non-null;} set of registers to exclude
 * @return {@code non-null;} an appropriately-constructed instance
 */
public RegisterSpecList subset(BitSet exclusionSet){
  int newSize=size() - exclusionSet.cardinality();
  if (newSize == 0) {
    return EMPTY;
  }
  RegisterSpecList result=new RegisterSpecList(newSize);
  int newIndex=0;
  for (int oldIndex=0; oldIndex < size(); oldIndex++) {
    if (!exclusionSet.get(oldIndex)) {
      result.set0(newIndex,get0(oldIndex));
      newIndex++;
    }
  }
  if (isImmutable()) {
    result.setImmutable();
  }
  return result;
}
