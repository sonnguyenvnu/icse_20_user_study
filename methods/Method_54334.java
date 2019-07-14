/** 
 * Unsafe version of:  {@link #bgfx_override_internal_texture override_internal_texture} 
 */
public static long nbgfx_override_internal_texture(short _handle,short _width,short _height,byte _numMips,int _format,long _flags){
  long __functionAddress=Functions.override_internal_texture;
  return invokeJP(_handle,_width,_height,_numMips,_format,_flags,__functionAddress);
}
