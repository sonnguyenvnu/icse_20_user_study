/** 
 * Unsafe version of:  {@link #bgfx_create_uniform create_uniform} 
 */
public static short nbgfx_create_uniform(long _name,int _type,short _num){
  long __functionAddress=Functions.create_uniform;
  return invokePS(_name,_type,_num,__functionAddress);
}
