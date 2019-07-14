/** 
 * Unsafe version of:  {@link #bgfx_get_shader_uniforms get_shader_uniforms}
 * @param _max maximum capacity of {@code _uniforms}
 */
public static short nbgfx_get_shader_uniforms(short _handle,long _uniforms,short _max){
  long __functionAddress=Functions.get_shader_uniforms;
  return invokePS(_handle,_uniforms,_max,__functionAddress);
}
