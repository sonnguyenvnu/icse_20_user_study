/** 
 * Array version of:  {@link #bgfx_get_shader_uniforms get_shader_uniforms} 
 */
@NativeType("uint16_t") public static short bgfx_get_shader_uniforms(@NativeType("bgfx_shader_handle_t") short _handle,@NativeType("bgfx_uniform_handle_t *") short[] _uniforms){
  long __functionAddress=Functions.get_shader_uniforms;
  return invokePS(_handle,_uniforms,(short)_uniforms.length,__functionAddress);
}
