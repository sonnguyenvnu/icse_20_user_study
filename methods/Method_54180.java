/** 
 * Unsafe version of:  {@link #bgfx_get_renderer_name get_renderer_name} 
 */
public static long nbgfx_get_renderer_name(int _type){
  long __functionAddress=Functions.get_renderer_name;
  return invokeP(_type,__functionAddress);
}
