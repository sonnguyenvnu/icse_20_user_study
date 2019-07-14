/** 
 * Returns the value of the  {@code bInheritHandle} field. 
 */
@NativeType("BOOL") public boolean bInheritHandle(){
  return nbInheritHandle(address()) != 0;
}
