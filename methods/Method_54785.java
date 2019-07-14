/** 
 * Unsafe version of  {@link #m_localVisualFrame(int) m_localVisualFrame}. 
 */
public static double nm_localVisualFrame(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3VisualShapeData.M_LOCALVISUALFRAME + check(index,7) * 8);
}
