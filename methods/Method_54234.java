/** 
 * Sets view scissor. Draw primitive outside view will be clipped. When  {@code _x},  {@code _y},  {@code _width} and {@code _height} are set to 0, scissorwill be disabled.
 * @param _id     view id
 * @param _x      position x from the left corner of the window
 * @param _y      position y from the top corner of the window
 * @param _width  width of scissor region
 * @param _height height of scissor region
 */
public static void bgfx_set_view_scissor(@NativeType("bgfx_view_id_t") int _id,@NativeType("uint16_t") int _x,@NativeType("uint16_t") int _y,@NativeType("uint16_t") int _width,@NativeType("uint16_t") int _height){
  nbgfx_set_view_scissor((short)_id,(short)_x,(short)_y,(short)_width,(short)_height);
}
