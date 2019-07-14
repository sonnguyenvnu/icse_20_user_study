/** 
 * Unsafe version of  {@link #m_localVisualFrame}. 
 */
public static DoubleBuffer nm_localVisualFrame(long struct){
  return memDoubleBuffer(struct + B3VisualShapeData.M_LOCALVISUALFRAME,7);
}
