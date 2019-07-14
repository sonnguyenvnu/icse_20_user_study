/** 
 * Unsafe version of:  {@link #bgfx_set_view_clear set_view_clear} 
 */
public static void nbgfx_set_view_clear(short _id,short _flags,int _rgba,float _depth,byte _stencil){
  long __functionAddress=Functions.set_view_clear;
  invokeV(_id,_flags,_rgba,_depth,_stencil,__functionAddress);
}
