/** 
 * Unsafe version of:  {@link #bgfx_dbg_text_image dbg_text_image} 
 */
public static void nbgfx_dbg_text_image(short _x,short _y,short _width,short _height,long _data,short _pitch){
  long __functionAddress=Functions.dbg_text_image;
  invokePV(_x,_y,_width,_height,_data,_pitch,__functionAddress);
}
