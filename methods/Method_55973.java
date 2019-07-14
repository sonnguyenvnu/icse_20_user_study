/** 
 * Sets the specified value to the  {@code res.mipmap.hMipmappedArray} field. 
 */
public CUDA_RESOURCE_DESC res_mipmap_hMipmappedArray(@NativeType("CUmipmappedArray") long value){
  nres_mipmap_hMipmappedArray(address(),value);
  return this;
}
