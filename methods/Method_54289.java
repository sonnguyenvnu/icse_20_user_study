/** 
 * Array version of:  {@link #bgfx_vertex_pack vertex_pack} 
 */
public static void bgfx_vertex_pack(@NativeType("float const *") float[] _input,@NativeType("bool") boolean _inputNormalized,@NativeType("bgfx_attrib_t") int _attr,@NativeType("bgfx_vertex_decl_t const *") BGFXVertexDecl _decl,@NativeType("void *") ByteBuffer _data,@NativeType("uint32_t") int _index){
  long __functionAddress=Functions.vertex_pack;
  if (CHECKS) {
    check(_input,4);
  }
  invokePPPV(_input,_inputNormalized,_attr,_decl.address(),memAddress(_data),_index,__functionAddress);
}
