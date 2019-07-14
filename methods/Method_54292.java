/** 
 * Array version of:  {@link #bgfx_topology_sort_tri_list topology_sort_tri_list} 
 */
public static void bgfx_topology_sort_tri_list(@NativeType("bgfx_topology_sort_t") int _sort,@NativeType("void *") ByteBuffer _dst,@NativeType("float const *") float[] _dir,@NativeType("float const *") float[] _pos,@NativeType("void const *") ByteBuffer _vertices,@NativeType("uint32_t") int _stride,@NativeType("void const *") ByteBuffer _indices,@NativeType("bool") boolean _index32){
  long __functionAddress=Functions.topology_sort_tri_list;
  if (CHECKS) {
    check(_dir,3);
    check(_pos,3);
  }
  invokePPPPPV(_sort,memAddress(_dst),_dst.remaining(),_dir,_pos,memAddress(_vertices),_stride,memAddress(_indices),_indices.remaining() >> (_index32 ? 2 : 1),_index32,__functionAddress);
}
