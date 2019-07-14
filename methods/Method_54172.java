/** 
 * Unsafe version of:  {@link #bgfx_vertex_convert vertex_convert} 
 */
public static void nbgfx_vertex_convert(long _dstDecl,long _dstData,long _srcDecl,long _srcData,int _num){
  long __functionAddress=Functions.vertex_convert;
  invokePPPPV(_dstDecl,_dstData,_srcDecl,_srcData,_num,__functionAddress);
}
