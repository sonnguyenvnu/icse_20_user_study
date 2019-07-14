/** 
 * Allocates transient vertex buffer.
 * @param _tvb  {@link BGFXTransientVertexBuffer} structure is filled and is valid for the duration of frame, and it can be reused for multiple draw calls
 * @param _num  number of vertices to allocate
 * @param _decl vertex declaration
 */
public static void bgfx_alloc_transient_vertex_buffer(@NativeType("bgfx_transient_vertex_buffer_t *") BGFXTransientVertexBuffer _tvb,@NativeType("uint32_t") int _num,@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _decl){
  nbgfx_alloc_transient_vertex_buffer(_tvb.address(),_num,_decl.address());
}
