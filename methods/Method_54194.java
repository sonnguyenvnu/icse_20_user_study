/** 
 * Prints into internal debug text character-buffer (VGA-compatible text mode).
 * @param _x      x coordinate
 * @param _y      y coordinate
 * @param _attr   color palette. Where top 4-bits represent index of background, and bottom 4-bits represent foreground color from standard VGA text palette (ANSIescape codes).
 * @param _format `printf` style format
 */
public static void bgfx_dbg_text_printf(@NativeType("uint16_t") int _x,@NativeType("uint16_t") int _y,@NativeType("uint8_t") int _attr,@NativeType("char const *") ByteBuffer _format){
  if (CHECKS) {
    checkNT1(_format);
  }
  nbgfx_dbg_text_printf((short)_x,(short)_y,(byte)_attr,memAddress(_format));
}
