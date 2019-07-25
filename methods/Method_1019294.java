/** 
 * @see java.util.BitSet#length()
 * @return Returns the "logical size" of this {@code BitSet}: the index of the highest set bit in the  {@code BitSet} plus one.
 */
public long length(){
  trimTrailingZeros();
  if (wlen == 0)   return 0;
  return (((long)wlen - 1) << 6) + (64 - Long.numberOfLeadingZeros(bits[wlen - 1]));
}
