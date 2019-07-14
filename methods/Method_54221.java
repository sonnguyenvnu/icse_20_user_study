/** 
 * Unsafe version of:  {@link #bgfx_create_texture_2d_scaled create_texture_2d_scaled} 
 */
public static short nbgfx_create_texture_2d_scaled(int _ratio,boolean _hasMips,short _numLayers,int _format,long _flags){
  long __functionAddress=Functions.create_texture_2d_scaled;
  return invokeJS(_ratio,_hasMips,_numLayers,_format,_flags,__functionAddress);
}
