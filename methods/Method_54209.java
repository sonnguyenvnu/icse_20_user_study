/** 
 * Returns number of requested or maximum available vertices.
 * @param _num  number of required vertices
 * @param _decl vertex declaration
 */
@NativeType("uint32_t") public static int bgfx_get_avail_transient_vertex_buffer(@NativeType("uint32_t") int _num,@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _decl){
  return nbgfx_get_avail_transient_vertex_buffer(_num,_decl.address());
}
