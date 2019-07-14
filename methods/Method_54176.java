/** 
 * Unsafe version of:  {@link #bgfx_topology_convert topology_convert}
 * @param _dstSize    destination index buffer in bytes. It must be large enough to contain output indices. If destination size is insufficient index buffer will betruncated.
 * @param _numIndices number of input indices
 */
public static int nbgfx_topology_convert(int _conversion,long _dst,int _dstSize,long _indices,int _numIndices,boolean _index32){
  long __functionAddress=Functions.topology_convert;
  return invokePPI(_conversion,_dst,_dstSize,_indices,_numIndices,_index32,__functionAddress);
}
