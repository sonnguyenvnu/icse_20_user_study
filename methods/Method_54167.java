/** 
 * Unsafe version of:  {@link #bgfx_vertex_decl_add vertex_decl_add} 
 */
public static long nbgfx_vertex_decl_add(long _decl,int _attrib,byte _num,int _type,boolean _normalized,boolean _asInt){
  long __functionAddress=Functions.vertex_decl_add;
  return invokePP(_decl,_attrib,_num,_type,_normalized,_asInt,__functionAddress);
}
