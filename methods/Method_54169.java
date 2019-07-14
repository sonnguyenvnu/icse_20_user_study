/** 
 * Unsafe version of:  {@link #bgfx_vertex_decl_skip vertex_decl_skip} 
 */
public static long nbgfx_vertex_decl_skip(long _decl,byte _num){
  long __functionAddress=Functions.vertex_decl_skip;
  return invokePP(_decl,_num,__functionAddress);
}
