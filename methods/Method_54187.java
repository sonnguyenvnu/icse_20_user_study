/** 
 * Makes reference to data to pass to bgfx. Unlike  {@link #bgfx_alloc alloc}, this call doesn't allocate memory for data. It just copies the  {@code _data} pointer.<p>Data passed must be available for at least 2  {@link #bgfx_frame frame} calls.</p>
 * @param _data the data to reference
 */
@Nullable @NativeType("bgfx_memory_t const *") public static BGFXMemory bgfx_make_ref(@NativeType("void const *") IntBuffer _data){
  long __result=nbgfx_make_ref(memAddress(_data),_data.remaining() << 2);
  return BGFXMemory.createSafe(__result);
}
