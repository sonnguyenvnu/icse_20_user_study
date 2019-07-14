/** 
 * Unsafe version of  {@link #m_rgbaColor(int,double) m_rgbaColor}. 
 */
public static void nm_rgbaColor(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3VisualShapeData.M_RGBACOLOR + check(index,4) * 8,value);
}
