/** 
 * Returns the value of the  {@code userData} field. 
 */
@NativeType("void *") public long userData(){
  return nuserData(address());
}
