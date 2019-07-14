/** 
 * Unsafe version of:  {@link #bgfx_update_texture_cube update_texture_cube} 
 */
public static void nbgfx_update_texture_cube(short _handle,short _layer,byte _side,byte _mip,short _x,short _y,short _width,short _height,long _mem,short _pitch){
  long __functionAddress=Functions.update_texture_cube;
  if (CHECKS) {
    BGFXMemory.validate(_mem);
  }
  invokePV(_handle,_layer,_side,_mip,_x,_y,_width,_height,_mem,_pitch,__functionAddress);
}
