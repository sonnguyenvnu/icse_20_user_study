/** 
 * Unsafe version of  {@link #m_visualShapeData(B3VisualShapeData.Buffer) m_visualShapeData}. 
 */
public static void nm_visualShapeData(long struct,B3VisualShapeData.Buffer value){
  memPutAddress(struct + B3VisualShapeInformation.M_VISUALSHAPEDATA,value.address());
  nm_numVisualShapes(struct,value.remaining());
}
