/** 
 * Unsafe version of  {@link #m_visualShapeData}. 
 */
public static B3VisualShapeData.Buffer nm_visualShapeData(long struct){
  return B3VisualShapeData.create(memGetAddress(struct + B3VisualShapeInformation.M_VISUALSHAPEDATA),nm_numVisualShapes(struct));
}
