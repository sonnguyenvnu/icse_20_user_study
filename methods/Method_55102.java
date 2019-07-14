/** 
 * Sets the specified value to the  {@code save_under} field. 
 */
public XSetWindowAttributes save_under(@NativeType("Bool") boolean value){
  nsave_under(address(),value ? 1 : 0);
  return this;
}
