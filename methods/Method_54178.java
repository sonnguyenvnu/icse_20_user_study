/** 
 * Unsafe version of:  {@link #bgfx_get_supported_renderers get_supported_renderers}
 * @param _max maximum number of elements in {@code _enum} array
 */
public static byte nbgfx_get_supported_renderers(byte _max,long _enum){
  long __functionAddress=Functions.get_supported_renderers;
  return invokePB(_max,_enum,__functionAddress);
}
