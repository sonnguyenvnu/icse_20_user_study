/** 
 * Unsafe version of  {@link #m_visualGeometryType(int) m_visualGeometryType}. 
 */
public static void nm_visualGeometryType(long struct,int value){
  UNSAFE.putInt(null,struct + B3VisualShapeData.M_VISUALGEOMETRYTYPE,value);
}
