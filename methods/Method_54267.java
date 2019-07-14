/** 
 * Unsafe version of:  {@link #bgfx_set_uniform set_uniform} 
 */
public static void nbgfx_set_uniform(short _handle,long _value,short _num){
  long __functionAddress=Functions.set_uniform;
  invokePV(_handle,_value,_num,__functionAddress);
}
