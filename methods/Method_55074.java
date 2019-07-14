/** 
 * Sets the specified value to the  {@code green_mask} field. 
 */
public Visual green_mask(@NativeType("unsigned long") long value){
  ngreen_mask(address(),value);
  return this;
}
