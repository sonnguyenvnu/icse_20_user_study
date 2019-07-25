/** 
 * @param i bit to get
 * @return true iff bit i is set
 */
public boolean get(int i){
  return (bits[i >> 5] & (1 << (i & 0x1F))) != 0;
}
