/** 
 * Unsafe version of:  {@link #bgfx_weld_vertices weld_vertices}
 * @param _num number of vertices in vertex stream
 */
public static short nbgfx_weld_vertices(long _output,long _decl,long _data,short _num,float _epsilon){
  long __functionAddress=Functions.weld_vertices;
  return invokePPPS(_output,_decl,_data,_num,_epsilon,__functionAddress);
}
