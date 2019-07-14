/** 
 * Returns the value at the specified index of the  {@code reserved} field. 
 */
@NativeType("unsigned int") public int reserved(int index){
  return nreserved(address(),index);
}
