/** 
 * Clears internal debug text buffer.
 * @param _attr  background color
 * @param _small default 8x16 or 8x8 font
 */
public static void bgfx_dbg_text_clear(@NativeType("uint8_t") int _attr,@NativeType("bool") boolean _small){
  nbgfx_dbg_text_clear((byte)_attr,_small);
}
