/** 
 * Clear the bits.
 * @param holder the int data containing the bits we're interestedin
 * @return the value of holder with the specified bits cleared(set to 0)
 */
public int clear(final int holder){
  return holder & ~_mask;
}
