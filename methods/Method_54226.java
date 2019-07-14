/** 
 * Unsafe version of:  {@link #bgfx_create_frame_buffer_from_handles create_frame_buffer_from_handles}
 * @param _num number of texture attachments
 */
public static short nbgfx_create_frame_buffer_from_handles(byte _num,long _handles,boolean _destroyTextures){
  long __functionAddress=Functions.create_frame_buffer_from_handles;
  return invokePS(_num,_handles,_destroyTextures,__functionAddress);
}
