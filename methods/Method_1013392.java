/** 
 * Return the bit at index <code>i</code>. 
 */
public boolean get(int i){
  int wd=i / 64;
  if (wd >= this.word.length)   return false;
  int bit=i % 64;
  return (this.word[wd] & (1L << bit)) != 0L;
}
