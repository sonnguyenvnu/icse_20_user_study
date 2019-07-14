/** 
 * Unsafe version of:  {@link #bgfx_create_frame_buffer_from_nwh create_frame_buffer_from_nwh} 
 */
public static short nbgfx_create_frame_buffer_from_nwh(long _nwh,short _width,short _height,int _format,int _depthFormat){
  long __functionAddress=Functions.create_frame_buffer_from_nwh;
  if (CHECKS) {
    check(_nwh);
  }
  return invokePS(_nwh,_width,_height,_format,_depthFormat,__functionAddress);
}
