/** 
 * Unsafe version of:  {@link #bgfx_topology_sort_tri_list topology_sort_tri_list}
 * @param _dstSize    destination index buffer in bytes. It must be large enough to contain output indices. If destination size is insufficient index buffer will betruncated.
 * @param _numIndices number of input indices
 */
public static void nbgfx_topology_sort_tri_list(int _sort,long _dst,int _dstSize,long _dir,long _pos,long _vertices,int _stride,long _indices,int _numIndices,boolean _index32){
  long __functionAddress=Functions.topology_sort_tri_list;
  invokePPPPPV(_sort,_dst,_dstSize,_dir,_pos,_vertices,_stride,_indices,_numIndices,_index32,__functionAddress);
}
