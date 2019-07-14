/** 
 * Unsafe version of:  {@link #bgfx_blit blit} 
 */
public static void nbgfx_blit(short _id,short _dst,byte _dstMip,short _dstX,short _dstY,short _dstZ,short _src,byte _srcMip,short _srcX,short _srcY,short _srcZ,short _width,short _height,short _depth){
  long __functionAddress=Functions.blit;
  invokeV(_id,_dst,_dstMip,_dstX,_dstY,_dstZ,_src,_srcMip,_srcX,_srcY,_srcZ,_width,_height,_depth,__functionAddress);
}
