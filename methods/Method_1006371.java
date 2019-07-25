/** 
 * Compares a RoaringBitmap and a BitSet. They are equal if and only if they contain the same set of integers.
 * @param bitset first object to be compared
 * @param bitmap second object to be compared
 * @return whether they are equal
 */
public static boolean equals(final BitSet bitset,final ImmutableRoaringBitmap bitmap){
  if (bitset.cardinality() != bitmap.getCardinality()) {
    return false;
  }
  final IntIterator it=bitmap.getIntIterator();
  while (it.hasNext()) {
    int val=it.next();
    if (!bitset.get(val)) {
      return false;
    }
  }
  return true;
}
