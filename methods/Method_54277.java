/** 
 * Unsafe version of:  {@link #bgfx_submit_occlusion_query submit_occlusion_query} 
 */
public static void nbgfx_submit_occlusion_query(short _id,short _program,short _occlusionQuery,int _depth,boolean _preserveState){
  long __functionAddress=Functions.submit_occlusion_query;
  invokeV(_id,_program,_occlusionQuery,_depth,_preserveState,__functionAddress);
}
