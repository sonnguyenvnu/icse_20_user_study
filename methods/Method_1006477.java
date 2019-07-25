/** 
 * Modifies the current bitmap by complementing the bits in the given range, from rangeStart (inclusive) rangeEnd (exclusive).
 * @param rangeStart inclusive beginning of range
 * @param rangeEnd exclusive ending of range
 * @deprecated use the version where longs specify the range
 */
@Deprecated public void flip(final int rangeStart,final int rangeEnd){
  if (rangeStart >= 0) {
    flip((long)rangeStart,(long)rangeEnd);
  }
 else {
    flip(rangeStart & 0xFFFFFFFFL,rangeEnd & 0xFFFFFFFFL);
  }
}
