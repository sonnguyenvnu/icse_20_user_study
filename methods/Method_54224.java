/** 
 * Returns texture direct access pointer. <p>Returns pointer to texture memory. If returned pointer is  {@code NULL} direct access is not available for this texture. If pointer is {@code UINTPTR_MAX}sentinel value it means texture is pending creation. Pointer returned can be cached and it will be valid until texture is destroyed.</p> <div style="margin-left: 26px; border-left: 1px solid gray; padding-left: 14px;"><h5>Note</h5> <p>Availability depends on:  {@link #BGFX_CAPS_TEXTURE_DIRECT_ACCESS CAPS_TEXTURE_DIRECT_ACCESS}. This feature is available on GPUs that have unified memory architecture (UMA) support.</p></div>
 */
@NativeType("void *") public static long bgfx_get_direct_access_ptr(@NativeType("bgfx_texture_handle_t") short _handle){
  long __functionAddress=Functions.get_direct_access_ptr;
  return invokeP(_handle,__functionAddress);
}
