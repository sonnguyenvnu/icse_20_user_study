/** 
 * Unsafe version of:  {@link #bgfx_vertex_pack vertex_pack} 
 */
public static void nbgfx_vertex_pack(long _input,boolean _inputNormalized,int _attr,long _decl,long _data,int _index){
  long __functionAddress=Functions.vertex_pack;
  invokePPPV(_input,_inputNormalized,_attr,_decl,_data,_index,__functionAddress);
}
