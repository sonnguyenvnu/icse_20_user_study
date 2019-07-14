/** 
 * Draws image into internal debug text buffer.
 * @param _x      x coordinate
 * @param _y      y coordinate
 * @param _width  image width
 * @param _height image height
 * @param _data   raw image data (character/attribute raw encoding)
 * @param _pitch  image pitch in bytes
 */
public static void bgfx_dbg_text_image(@NativeType("uint16_t") int _x,@NativeType("uint16_t") int _y,@NativeType("uint16_t") int _width,@NativeType("uint16_t") int _height,@NativeType("void const *") ByteBuffer _data,@NativeType("uint16_t") int _pitch){
  if (CHECKS) {
    check(_data,_height * _pitch);
  }
  nbgfx_dbg_text_image((short)_x,(short)_y,(short)_width,(short)_height,memAddress(_data),(short)_pitch);
}
