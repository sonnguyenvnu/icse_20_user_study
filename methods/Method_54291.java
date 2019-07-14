/** 
 * Array version of:  {@link #bgfx_weld_vertices weld_vertices} 
 */
@NativeType("uint16_t") public static short bgfx_weld_vertices(@NativeType("uint16_t *") short[] _output,@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _decl,@NativeType("void const *") ByteBuffer _data,float _epsilon){
  long __functionAddress=Functions.weld_vertices;
  return invokePPPS(_output,_decl.address(),memAddress(_data),(short)_output.length,_epsilon,__functionAddress);
}
