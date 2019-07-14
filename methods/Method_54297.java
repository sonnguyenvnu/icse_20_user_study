/** 
 * Returns a  {@link BGFXAllocatorVtbl} view of the struct pointed to by the {@code vtbl} field. 
 */
@NativeType("bgfx_allocator_vtbl_t const *") public BGFXAllocatorVtbl vtbl(){
  return nvtbl(address());
}
