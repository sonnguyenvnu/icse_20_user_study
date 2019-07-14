/** 
 * Unsafe version of:  {@link #bgfx_set_view_mode set_view_mode} 
 */
public static void nbgfx_set_view_mode(short _id,int _mode){
  long __functionAddress=Functions.set_view_mode;
  invokeV(_id,_mode,__functionAddress);
}
