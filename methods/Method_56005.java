/** 
 * Sets the specified value to the  {@code maxAnisotropy} field. 
 */
public CUDA_TEXTURE_DESC maxAnisotropy(@NativeType("unsigned int") int value){
  nmaxAnisotropy(address(),value);
  return this;
}
