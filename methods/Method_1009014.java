/** 
 * Set the bits.
 * @param holder the int data containing the bits we're interestedin
 * @return the value of holder with the specified bits set to 1
 */
public int set(final int holder){
  return holder | _mask;
}
