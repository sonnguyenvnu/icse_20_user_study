/** 
 * Unsafe version of  {@link #m_numVisualShapes}. 
 */
public static int nm_numVisualShapes(long struct){
  return UNSAFE.getInt(null,struct + B3VisualShapeInformation.M_NUMVISUALSHAPES);
}
