/** 
 * Unsafe version of:  {@link #bgfx_alloc_transient_buffers alloc_transient_buffers} 
 */
public static boolean nbgfx_alloc_transient_buffers(long _tvb,long _decl,int _numVertices,long _tib,int _numIndices){
  long __functionAddress=Functions.alloc_transient_buffers;
  return invokePPPZ(_tvb,_decl,_numVertices,_tib,_numIndices,__functionAddress);
}
