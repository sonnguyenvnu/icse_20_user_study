/** 
 * Unsafe version of:  {@link #bgfx_create_frame_buffer_from_attachment create_frame_buffer_from_attachment}
 * @param _num number of texture attachments
 */
public static short nbgfx_create_frame_buffer_from_attachment(byte _num,long _attachment,boolean _destroyTextures){
  long __functionAddress=Functions.create_frame_buffer_from_attachment;
  return invokePS(_num,_attachment,_destroyTextures,__functionAddress);
}
