/** 
 * Unsafe version of  {@link #m_localVisualFrame(int,double) m_localVisualFrame}. 
 */
public static void nm_localVisualFrame(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3VisualShapeData.M_LOCALVISUALFRAME + check(index,7) * 8,value);
}
