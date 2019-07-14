/** 
 * Sets the specified value to the  {@code lastMipmapLevel} field. 
 */
public CUDA_RESOURCE_VIEW_DESC lastMipmapLevel(@NativeType("unsigned int") int value){
  nlastMipmapLevel(address(),value);
  return this;
}
