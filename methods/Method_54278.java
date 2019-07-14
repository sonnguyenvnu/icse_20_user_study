/** 
 * Unsafe version of:  {@link #bgfx_submit_indirect submit_indirect} 
 */
public static void nbgfx_submit_indirect(short _id,short _program,short _indirectHandle,short _start,short _num,int _depth,boolean _preserveState){
  long __functionAddress=Functions.submit_indirect;
  invokeV(_id,_program,_indirectHandle,_start,_num,_depth,_preserveState,__functionAddress);
}
