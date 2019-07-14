/** 
 * Unsafe version of  {@link #m_rgbaColor}. 
 */
public static DoubleBuffer nm_rgbaColor(long struct){
  return memDoubleBuffer(struct + B3VisualShapeData.M_RGBACOLOR,4);
}
