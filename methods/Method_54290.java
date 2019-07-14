/** 
 * Array version of:  {@link #bgfx_vertex_unpack vertex_unpack} 
 */
public static void bgfx_vertex_unpack(@NativeType("float *") float[] _output,@NativeType("bgfx_attrib_t") int _attr,@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _decl,@NativeType("void const *") ByteBuffer _data,@NativeType("uint32_t") int _index){
  long __functionAddress=Functions.vertex_unpack;
  if (CHECKS) {
    check(_output,4);
  }
  invokePPPV(_output,_attr,_decl.address(),memAddress(_data),_index,__functionAddress);
}
