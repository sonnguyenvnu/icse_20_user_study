/** 
 * Unsafe version of  {@link #m_rgbaColor(DoubleBuffer) m_rgbaColor}. 
 */
public static void nm_rgbaColor(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,4);
  }
  memCopy(memAddress(value),struct + B3VisualShapeData.M_RGBACOLOR,value.remaining() * 8);
}
