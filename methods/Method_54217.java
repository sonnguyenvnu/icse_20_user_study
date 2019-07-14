/** 
 * Returns the number of uniforms and uniform handles used inside shader. <p>Only non-predefined uniforms are returned.</p>
 * @param _handle   shader handle
 * @param _uniforms {@code bgfx_uniform_handle_t} array where data will be stored
 * @return number of uniforms used by shader
 */
@NativeType("uint16_t") public static short bgfx_get_shader_uniforms(@NativeType("bgfx_shader_handle_t") short _handle,@NativeType("bgfx_uniform_handle_t *") ShortBuffer _uniforms){
  return nbgfx_get_shader_uniforms(_handle,memAddress(_uniforms),(short)_uniforms.remaining());
}
