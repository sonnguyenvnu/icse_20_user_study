/** 
 * Sets the specified value to the  {@code ext_data} field. 
 */
public Visual ext_data(@NativeType("void *") long value){
  next_data(address(),value);
  return this;
}
