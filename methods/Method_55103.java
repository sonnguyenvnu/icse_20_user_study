/** 
 * Sets the specified value to the  {@code do_not_propagate_mask} field. 
 */
public XSetWindowAttributes do_not_propagate_mask(long value){
  ndo_not_propagate_mask(address(),value);
  return this;
}
