/** 
 * Unsafe version of:  {@link #bgfx_vertex_decl_decode vertex_decl_decode} 
 */
public static void nbgfx_vertex_decl_decode(long _decl,int _attrib,long _num,long _type,long _normalized,long _asInt){
  long __functionAddress=Functions.vertex_decl_decode;
  invokePPPPPV(_decl,_attrib,_num,_type,_normalized,_asInt,__functionAddress);
}
