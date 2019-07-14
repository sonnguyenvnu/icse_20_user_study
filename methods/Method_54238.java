/** 
 * Unsafe version of:  {@link #bgfx_set_view_transform set_view_transform} 
 */
public static void nbgfx_set_view_transform(short _id,long _view,long _proj){
  long __functionAddress=Functions.set_view_transform;
  invokePPV(_id,_view,_proj,__functionAddress);
}
