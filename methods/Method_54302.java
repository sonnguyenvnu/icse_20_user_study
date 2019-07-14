/** 
 * Sets the specified value to the  {@code capture_begin} field. 
 */
public BGFXCallbackVtbl capture_begin(@NativeType("void (*) (bgfx_callback_interface_t *, uint32_t, uint32_t, uint32_t, bgfx_texture_format_t, bool)") BGFXCaptureBeginCallbackI value){
  ncapture_begin(address(),value);
  return this;
}
