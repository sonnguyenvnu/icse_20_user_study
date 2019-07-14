/** 
 * Sets the specified value to the  {@code background_pixmap} field. 
 */
public XSetWindowAttributes background_pixmap(@NativeType("Pixmap") long value){
  nbackground_pixmap(address(),value);
  return this;
}
