/** 
 * @return Returns an iterator over all set bits of this bitset. The iterator shouldbe faster than using a loop around  {@link #nextSetBit(int)}.
 */
public BitSetIterator iterator(){
  return new BitSetIterator(bits,wlen);
}
