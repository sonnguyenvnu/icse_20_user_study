/** 
 * Sets the specified value to the  {@code red_mask} field. 
 */
public XVisualInfo red_mask(@NativeType("unsigned long") long value){
  nred_mask(address(),value);
  return this;
}
