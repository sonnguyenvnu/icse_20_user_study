/** 
 * Unsafe version of:  {@link #bgfx_set_view_clear_mrt set_view_clear_mrt} 
 */
public static void nbgfx_set_view_clear_mrt(short _id,short _flags,float _depth,byte _stencil,byte _0,byte _1,byte _2,byte _3,byte _4,byte _5,byte _6,byte _7){
  long __functionAddress=Functions.set_view_clear_mrt;
  invokeV(_id,_flags,_depth,_stencil,_0,_1,_2,_3,_4,_5,_6,_7,__functionAddress);
}
