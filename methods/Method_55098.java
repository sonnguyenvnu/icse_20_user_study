/** 
 * Sets the specified value to the  {@code border_pixmap} field. 
 */
public XSetWindowAttributes border_pixmap(@NativeType("Pixmap") long value){
  nborder_pixmap(address(),value);
  return this;
}
