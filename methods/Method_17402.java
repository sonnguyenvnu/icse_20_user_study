/** 
 * Initializes and increases the capacity of this <tt>BloomFilter</tt> instance, if necessary, to ensure that it can accurately estimate the membership of elements given the expected number of insertions. This operation forgets all previous memberships when resizing.
 * @param expectedInsertions the number of expected insertions
 * @param fpp the false positive probability, where 0.0 > fpp < 1.0
 */
void ensureCapacity(@NonNegative long expectedInsertions,@NonNegative double fpp){
  checkArgument(expectedInsertions >= 0);
  checkArgument(fpp > 0 && fpp < 1);
  double optimalBitsFactor=-Math.log(fpp) / (Math.log(2) * Math.log(2));
  int optimalNumberOfBits=(int)(expectedInsertions * optimalBitsFactor);
  int optimalSize=optimalNumberOfBits >>> BITS_PER_LONG_SHIFT;
  if ((table != null) && (table.length >= optimalSize)) {
    return;
  }
 else   if (optimalSize == 0) {
    tableShift=Integer.SIZE - 1;
    table=new long[1];
  }
 else {
    int powerOfTwoShift=Integer.SIZE - Integer.numberOfLeadingZeros(optimalSize - 1);
    tableShift=Integer.SIZE - powerOfTwoShift;
    table=new long[1 << powerOfTwoShift];
  }
}
