/** 
 * Unsafe version of:  {@link #bgfx_encoder_submit_occlusion_query encoder_submit_occlusion_query} 
 */
public static void nbgfx_encoder_submit_occlusion_query(long _encoder,short _id,short _program,short _occlusionQuery,int _depth,boolean _preserveState){
  long __functionAddress=Functions.encoder_submit_occlusion_query;
  if (CHECKS) {
    check(_encoder);
  }
  invokePV(_encoder,_id,_program,_occlusionQuery,_depth,_preserveState,__functionAddress);
}
