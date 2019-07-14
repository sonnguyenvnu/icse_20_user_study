/** 
 * Print into internal debug text character-buffer (VGA-compatible text mode).
 * @param _x       x coordinate
 * @param _y       y coordinate
 * @param _attr    color palette. Where top 4-bits represent index of background, and bottom 4-bits represent foreground color from standard VGA text palette.
 * @param _format  `printf` style format
 * @param _argList additional arguments for format string
 */
public static void bgfx_dbg_text_vprintf(@NativeType("uint16_t") int _x,@NativeType("uint16_t") int _y,@NativeType("uint8_t") int _attr,@NativeType("char const *") CharSequence _format,@NativeType("va_list") long _argList){
  MemoryStack stack=stackGet();
  int stackPointer=stack.getPointer();
  try {
    stack.nASCII(_format,true);
    long _formatEncoded=stack.getPointerAddress();
    nbgfx_dbg_text_vprintf((short)_x,(short)_y,(byte)_attr,_formatEncoded,_argList);
  }
  finally {
    stack.setPointer(stackPointer);
  }
}
