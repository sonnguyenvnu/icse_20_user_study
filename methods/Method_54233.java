/** 
 * Unsafe version of:  {@link #bgfx_get_result get_result} 
 */
public static int nbgfx_get_result(short _handle,long _result){
  long __functionAddress=Functions.get_result;
  return invokePI(_handle,_result,__functionAddress);
}
