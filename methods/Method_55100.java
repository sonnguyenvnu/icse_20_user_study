/** 
 * Sets the specified value to the  {@code backing_planes} field. 
 */
public XSetWindowAttributes backing_planes(@NativeType("unsigned long") long value){
  nbacking_planes(address(),value);
  return this;
}
