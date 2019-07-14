/** 
 * Sets the specified value to the  {@code showCmd} field. 
 */
public WINDOWPLACEMENT showCmd(@NativeType("UINT") int value){
  nshowCmd(address(),value);
  return this;
}
