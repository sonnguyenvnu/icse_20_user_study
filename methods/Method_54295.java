/** 
 * Array version of:  {@link #bgfx_get_supported_renderers get_supported_renderers} 
 */
@NativeType("uint8_t") public static byte bgfx_get_supported_renderers(@NativeType("bgfx_renderer_type_t *") int[] _enum){
  long __functionAddress=Functions.get_supported_renderers;
  return invokePB((byte)_enum.length,_enum,__functionAddress);
}
