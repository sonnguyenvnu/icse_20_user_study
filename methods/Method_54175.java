/** 
 * Welds vertices.
 * @param _output  welded vertices remapping table. The size of buffer be the same as number of vertices.
 * @param _decl    vertex stream declaration
 * @param _data    vertex stream
 * @param _epsilon error tolerance for vertex position comparison
 * @return number of unique vertices after vertex welding
 */
@NativeType("uint16_t") public static short bgfx_weld_vertices(@NativeType("uint16_t *") ShortBuffer _output,@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _decl,@NativeType("void const *") ByteBuffer _data,float _epsilon){
  return nbgfx_weld_vertices(memAddress(_output),_decl.address(),memAddress(_data),(short)_output.remaining(),_epsilon);
}
