/** 
 * Unsafe version of  {@link #m_dimensions}. 
 */
public static DoubleBuffer nm_dimensions(long struct){
  return memDoubleBuffer(struct + B3VisualShapeData.M_DIMENSIONS,3);
}
