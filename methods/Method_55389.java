/** 
 * Sets the specified value to the  {@code nLength} field. 
 */
public SECURITY_ATTRIBUTES nLength(@NativeType("DWORD") int value){
  nnLength(address(),value);
  return this;
}
