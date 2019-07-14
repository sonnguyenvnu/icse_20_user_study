/** 
 * Unsafe version of:  {@link #bgfx_vertex_unpack vertex_unpack} 
 */
public static void nbgfx_vertex_unpack(long _output,int _attr,long _decl,long _data,int _index){
  long __functionAddress=Functions.vertex_unpack;
  invokePPPV(_output,_attr,_decl,_data,_index,__functionAddress);
}
