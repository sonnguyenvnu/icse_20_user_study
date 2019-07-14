/** 
 * Unsafe version of:  {@link #bgfx_get_texture get_texture} 
 */
public static short nbgfx_get_texture(short _handle,byte _attachment){
  long __functionAddress=Functions.get_texture;
  return invokeS(_handle,_attachment,__functionAddress);
}
