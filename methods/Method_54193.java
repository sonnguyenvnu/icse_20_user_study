/** 
 * Unsafe version of:  {@link #bgfx_dbg_text_printf dbg_text_printf} 
 */
public static void nbgfx_dbg_text_printf(short _x,short _y,byte _attr,long _format){
  long __functionAddress=Functions.dbg_text_printf;
  invokePV(_x,_y,_attr,_format,__functionAddress);
}
