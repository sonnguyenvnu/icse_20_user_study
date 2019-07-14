/** 
 * Sets the specified value to the  {@code override_redirect} field. 
 */
public XSetWindowAttributes override_redirect(@NativeType("Bool") boolean value){
  noverride_redirect(address(),value ? 1 : 0);
  return this;
}
