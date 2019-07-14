/** 
 * Returns supported backend API renderers.
 * @param _enum array where supported renderers will be written
 * @return the number of renderers written to {@code _enum}
 */
@NativeType("uint8_t") public static byte bgfx_get_supported_renderers(@NativeType("bgfx_renderer_type_t *") IntBuffer _enum){
  return nbgfx_get_supported_renderers((byte)_enum.remaining(),memAddress(_enum));
}
