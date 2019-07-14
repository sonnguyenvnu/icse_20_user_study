/** 
 * Returns a  {@link BGFXAllocatorInterface} view of the struct pointed to by the {@code allocator} field. 
 */
@Nullable @NativeType("bgfx_allocator_interface_t *") public BGFXAllocatorInterface allocator(){
  return nallocator(address());
}
