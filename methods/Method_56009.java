/** 
 * Sets the specified value to the  {@code sharedMemPerBlock} field. 
 */
public CUdevprop sharedMemPerBlock(int value){
  nsharedMemPerBlock(address(),value);
  return this;
}
