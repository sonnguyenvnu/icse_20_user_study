/** 
 * Sets the specified value to the  {@code backing_pixel} field. 
 */
public XSetWindowAttributes backing_pixel(@NativeType("unsigned long") long value){
  nbacking_pixel(address(),value);
  return this;
}
