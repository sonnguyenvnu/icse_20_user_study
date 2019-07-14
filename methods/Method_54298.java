/** 
 * Returns a  {@link BGFXCallbackVtbl} view of the struct pointed to by the {@code vtbl} field. 
 */
@NativeType("bgfx_callback_vtbl_t const *") public BGFXCallbackVtbl vtbl(){
  return nvtbl(address());
}
