/** 
 * Requests screen shot.
 * @param _handle   frame buffer handle
 * @param _filePath will be passed to {@link BGFXScreenShotCallback}
 */
public static void bgfx_request_screen_shot(@NativeType("bgfx_frame_buffer_handle_t") short _handle,@NativeType("char const *") CharSequence _filePath){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(_filePath,true);
    long _filePathEncoded=stack.getPointerAddress();
    nbgfx_request_screen_shot(_handle,_filePathEncoded);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
