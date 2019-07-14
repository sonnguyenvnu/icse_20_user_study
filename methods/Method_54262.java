/** 
 * Sets condition for rendering.
 * @param _handle  occlusion query handle
 * @param _visible render if occlusion query is visible
 */
public static void bgfx_set_condition(@NativeType("bgfx_occlusion_query_handle_t") short _handle,@NativeType("bool") boolean _visible){
  long __functionAddress=Functions.set_condition;
  invokeV(_handle,_visible,__functionAddress);
}
