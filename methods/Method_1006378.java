/** 
 * Checks if the bitmap contains the range.
 * @param minimum the inclusive lower bound of the range
 * @param supremum the exclusive upper bound of the range
 * @return whether the bitmap intersects with the range
 */
public boolean contains(long minimum,long supremum){
  MutableRoaringBitmap.rangeSanityCheck(minimum,supremum);
  short firstKey=highbits(minimum);
  short lastKey=highbits(supremum);
  int span=BufferUtil.toIntUnsigned(lastKey) - BufferUtil.toIntUnsigned(firstKey);
  int len=highLowContainer.size();
  if (len < span) {
    return false;
  }
  int begin=highLowContainer.getIndex(firstKey);
  int end=highLowContainer.getIndex(lastKey);
  end=end < 0 ? -end - 1 : end;
  if (begin < 0 || end - begin != span) {
    return false;
  }
  int min=(short)minimum & 0xFFFF;
  int sup=(short)supremum & 0xFFFF;
  if (firstKey == lastKey) {
    return highLowContainer.getContainerAtIndex(begin).contains(min,sup);
  }
  if (!highLowContainer.getContainerAtIndex(begin).contains(min,1 << 16)) {
    return false;
  }
  if (end < len && !highLowContainer.getContainerAtIndex(end).contains(0,sup)) {
    return false;
  }
  for (int i=begin + 1; i < end; ++i) {
    if (highLowContainer.getContainerAtIndex(i).getCardinality() != 1 << 16) {
      return false;
    }
  }
  return true;
}
