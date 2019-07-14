/** 
 * Unsafe version of:  {@link #bgfx_request_screen_shot request_screen_shot} 
 */
public static void nbgfx_request_screen_shot(short _handle,long _filePath){
  long __functionAddress=Functions.request_screen_shot;
  invokePV(_handle,_filePath,__functionAddress);
}
