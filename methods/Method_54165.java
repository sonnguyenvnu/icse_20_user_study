/** 
 * Init attachment.
 * @param _handle  render target texture handle
 * @param _access  access. One of:<br><table><tr><td>{@link #BGFX_ACCESS_READ ACCESS_READ}</td><td> {@link #BGFX_ACCESS_WRITE ACCESS_WRITE}</td><td> {@link #BGFX_ACCESS_READWRITE ACCESS_READWRITE}</td></tr></table>
 * @param _layer   cubemap side or depth layer/slice
 * @param _mip     mip level
 * @param _resolve resolve flags. One of:<br><table><tr><td>{@link #BGFX_RESOLVE_NONE RESOLVE_NONE}</td><td> {@link #BGFX_RESOLVE_AUTO_GEN_MIPS RESOLVE_AUTO_GEN_MIPS}</td></tr></table>
 */
public static void bgfx_attachment_init(@NativeType("bgfx_attachment_t *") BGFXAttachment _this,@NativeType("bgfx_texture_handle_t") short _handle,@NativeType("bgfx_access_t") int _access,@NativeType("uint16_t") int _layer,@NativeType("uint16_t") int _mip,@NativeType("uint8_t") int _resolve){
  nbgfx_attachment_init(_this.address(),_handle,_access,(short)_layer,(short)_mip,(byte)_resolve);
}
