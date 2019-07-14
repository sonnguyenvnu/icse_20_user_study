/** 
 * Returns the value of the  {@code res.linear.devPtr} field. 
 */
@NativeType("CUdeviceptr") public long res_linear_devPtr(){
  return nres_linear_devPtr(address());
}
