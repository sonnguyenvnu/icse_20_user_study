/** 
 * Returns the value of the  {@code cache_read_size} field. 
 */
@NativeType("uint32_t (*) (bgfx_callback_interface_t *, uint64_t)") public BGFXCacheReadSizeCallback cache_read_size(){
  return ncache_read_size(address());
}
