/** 
 * Array version of:  {@link #bgfx_topology_sort_tri_list topology_sort_tri_list} 
 */
public static void bgfx_topology_sort_tri_list(@NativeType("bgfx_topology_sort_t") int _sort,@NativeType("void *") short[] _dst,@NativeType("float const *") float[] _dir,@NativeType("float const *") float[] _pos,@NativeType("void const *") ByteBuffer _vertices,@NativeType("uint32_t") int _stride,@NativeType("void const *") short[] _indices,@NativeType("bool") boolean _index32){
  long __functionAddress=Functions.topology_sort_tri_list;
  if (CHECKS) {
    check(_dir,3);
    check(_pos,3);
  }
  invokePPPPPV(_sort,_dst,_dst.length << 1,_dir,_pos,memAddress(_vertices),_stride,_indices,_indices.length,_index32,__functionAddress);
}
