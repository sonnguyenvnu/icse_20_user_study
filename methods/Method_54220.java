/** 
 * Unsafe version of:  {@link #bgfx_calc_texture_size calc_texture_size} 
 */
public static void nbgfx_calc_texture_size(long _info,short _width,short _height,short _depth,boolean _cubeMap,boolean _hasMips,short _numLayers,int _format){
  long __functionAddress=Functions.calc_texture_size;
  invokePV(_info,_width,_height,_depth,_cubeMap,_hasMips,_numLayers,_format,__functionAddress);
}
