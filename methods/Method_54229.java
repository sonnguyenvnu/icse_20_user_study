/** 
 * Unsafe version of:  {@link #bgfx_set_frame_buffer_name set_frame_buffer_name}
 * @param _len frame buffer name length (if length is {@code INT32_MAX}, it's expected that  {@code _name} is zero terminated string)
 */
public static void nbgfx_set_frame_buffer_name(short _handle,long _name,int _len){
  long __functionAddress=Functions.set_frame_buffer_name;
  invokePV(_handle,_name,_len,__functionAddress);
}
