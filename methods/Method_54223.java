/** 
 * Unsafe version of:  {@link #bgfx_read_texture read_texture} 
 */
public static int nbgfx_read_texture(short _handle,long _data,byte _mip){
  long __functionAddress=Functions.read_texture;
  return invokePI(_handle,_data,_mip,__functionAddress);
}
