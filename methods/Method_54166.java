/** 
 * Unsafe version of:  {@link #bgfx_vertex_decl_begin vertex_decl_begin} 
 */
public static long nbgfx_vertex_decl_begin(long _decl,int _renderer){
  long __functionAddress=Functions.vertex_decl_begin;
  return invokePP(_decl,_renderer,__functionAddress);
}
