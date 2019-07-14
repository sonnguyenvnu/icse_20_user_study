/** 
 * Advances to next frame. When using multithreaded renderer, this call just swaps internal buffers, kicks render thread, and returns. In singlethreaded renderer this call does frame rendering.
 * @param _capture capture frame with graphics debugger
 * @return current frame number. This might be used in conjunction with double/multi buffering data outside the library and passing it to library {@link #bgfx_make_ref make_ref}calls.
 */
@NativeType("uint32_t") public static int bgfx_frame(@NativeType("bool") boolean _capture){
  long __functionAddress=Functions.frame;
  return invokeI(_capture,__functionAddress);
}
