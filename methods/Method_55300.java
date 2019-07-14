/** 
 * Returns the value of the  {@code StateFlags} field. 
 */
@NativeType("DWORD") public int StateFlags(){
  return nStateFlags(address());
}
