/** 
 * Unsafe version of  {@link #m_visualGeometryType}. 
 */
public static int nm_visualGeometryType(long struct){
  return UNSAFE.getInt(null,struct + B3VisualShapeData.M_VISUALGEOMETRYTYPE);
}
