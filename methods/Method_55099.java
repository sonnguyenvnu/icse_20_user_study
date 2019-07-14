/** 
 * Sets the specified value to the  {@code border_pixel} field. 
 */
public XSetWindowAttributes border_pixel(@NativeType("unsigned long") long value){
  nborder_pixel(address(),value);
  return this;
}
