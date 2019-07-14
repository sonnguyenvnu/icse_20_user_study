/** 
 * Unsafe version of  {@link #m_rgbaColor(int) m_rgbaColor}. 
 */
public static double nm_rgbaColor(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3VisualShapeData.M_RGBACOLOR + check(index,4) * 8);
}
