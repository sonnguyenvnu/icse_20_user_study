/** 
 * Sets the specified value to the  {@code background_pixel} field. 
 */
public XSetWindowAttributes background_pixel(@NativeType("unsigned long") long value){
  nbackground_pixel(address(),value);
  return this;
}
