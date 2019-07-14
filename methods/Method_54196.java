/** 
 * Unsafe version of:  {@link #bgfx_dbg_text_vprintf dbg_text_vprintf} 
 */
public static void nbgfx_dbg_text_vprintf(short _x,short _y,byte _attr,long _format,long _argList){
  long __functionAddress=Functions.dbg_text_vprintf;
  if (CHECKS) {
    check(_argList);
  }
  invokePPV(_x,_y,_attr,_format,_argList,__functionAddress);
}
