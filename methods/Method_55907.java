/** 
 * Returns a  {@link CUDA_ARRAY3D_DESCRIPTOR} view of the {@code arrayDesc} field. 
 */
public CUDA_ARRAY3D_DESCRIPTOR arrayDesc(){
  return narrayDesc(address());
}
