/** 
 * Converts vertex stream data from one vertex stream format to another.
 * @param _dstDecl destination vertex stream declaration
 * @param _dstData destination vertex stream
 * @param _srcDecl source vertex stream declaration
 * @param _srcData source vertex stream data
 * @param _num     number of vertices to convert from source to destination
 */
public static void bgfx_vertex_convert(@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _dstDecl,@NativeType("void *") ByteBuffer _dstData,@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _srcDecl,@NativeType("void const *") ByteBuffer _srcData,@NativeType("uint32_t") int _num){
  nbgfx_vertex_convert(_dstDecl.address(),memAddress(_dstData),_srcDecl.address(),memAddress(_srcData),_num);
}
