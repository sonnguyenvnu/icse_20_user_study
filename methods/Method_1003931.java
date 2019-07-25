/** 
 * Return the level of the biggest element (using the indices array 'ids' to track which elements have been already returned). Every buffer has already been sorted at this point.
 * @return the level of the biggest element or -1 if no element has been found
 */
@VisibleForTesting int biggest(final int[] ids){
  long biggest=Long.MIN_VALUE;
  final int id0=ids[0], id1=ids[1];
  int iBiggest=-1;
  if (0 < leafCount && 0 <= id0) {
    biggest=buffer[0][id0];
    iBiggest=0;
  }
  if (bufferSize < leafCount && 0 <= id1) {
    long x=buffer[1][id1];
    if (x > biggest) {
      biggest=x;
      iBiggest=1;
    }
  }
  for (int i=2; i < currentTop + 1; i++) {
    if (!isBufferEmpty(i) && 0 <= ids[i]) {
      long x=buffer[i][ids[i]];
      if (x > biggest) {
        biggest=x;
        iBiggest=i;
      }
    }
  }
  return iBiggest;
}
