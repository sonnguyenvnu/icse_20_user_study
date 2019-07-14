/** 
 * Unsafe version of:  {@link #bgfx_dispatch dispatch} 
 */
public static void nbgfx_dispatch(short _id,short _program,int _numX,int _numY,int _numZ){
  long __functionAddress=Functions.dispatch;
  invokeV(_id,_program,_numX,_numY,_numZ,__functionAddress);
}
