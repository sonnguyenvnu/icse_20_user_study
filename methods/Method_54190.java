/** 
 * Makes reference to data to pass to bgfx. Unlike  {@link #bgfx_alloc alloc}, this call doesn't allocate memory for data. It just copies the  {@code _data} pointer.<p>The  {@code bgfx_release_fn_t} function pointer will release this memory after it's consumed. The {@code bgfx_release_fn_t} function must be able to becalled from any thread.</p>
 * @param _data      the data to reference
 * @param _releaseFn callback function to release memory after use
 * @param _userData  user data to be passed to callback function
 */
@Nullable @NativeType("bgfx_memory_t const *") public static BGFXMemory bgfx_make_ref_release(@NativeType("void const *") PointerBuffer _data,@NativeType("bgfx_release_fn_t") BGFXReleaseFunctionCallbackI _releaseFn,@NativeType("void *") long _userData){
  long __result=nbgfx_make_ref_release(memAddress(_data),_data.remaining() << POINTER_SHIFT,_releaseFn.address(),_userData);
  return BGFXMemory.createSafe(__result);
}
