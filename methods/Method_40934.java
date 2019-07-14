/** 
 * Copies the most recent bits from the  {@code left} BitSet to the {@code right} BitSet in order from oldest tonewest.
 */
static void copyBits(CircularBitSet left,CircularBitSet right){
  int bitsToCopy=Math.min(left.occupiedBits,right.size);
  int index=left.nextIndex - bitsToCopy;
  if (index < 0)   index+=left.occupiedBits;
  for (int i=0; i < bitsToCopy; i++, index=left.indexAfter(index))   right.setNext(left.bitSet.get(index));
}
