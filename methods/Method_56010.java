/** 
 * Sets the specified value to the  {@code regsPerBlock} field. 
 */
public CUdevprop regsPerBlock(int value){
  nregsPerBlock(address(),value);
  return this;
}
