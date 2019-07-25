/** 
 * Sets the given bit to  {@code false}.
 * @param bits {@code non-null;} bit set to operate on
 * @param idx {@code >= 0, < getMax(set);} which bit
 */
public static void clear(int[] bits,int idx){
  int arrayIdx=idx >> 5;
  int bit=1 << (idx & 0x1f);
  bits[arrayIdx]&=~bit;
}
