/** 
 * Unsafe version of:  {@link #bgfx_attachment_init attachment_init} 
 */
public static void nbgfx_attachment_init(long _this,short _handle,int _access,short _layer,short _mip,byte _resolve){
  long __functionAddress=Functions.attachment_init;
  invokePV(_this,_handle,_access,_layer,_mip,_resolve,__functionAddress);
}
