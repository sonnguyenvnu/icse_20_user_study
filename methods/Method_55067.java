/** 
 * Returns the value of the  {@code ext_data} field. 
 */
@NativeType("void *") public long ext_data(){
  return next_data(address());
}
