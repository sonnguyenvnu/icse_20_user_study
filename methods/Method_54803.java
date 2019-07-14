/** 
 * Returns a  {@link B3VisualShapeData.Buffer} view of the struct array pointed to by the {@code m_visualShapeData} field. 
 */
@NativeType("struct b3VisualShapeData *") public B3VisualShapeData.Buffer m_visualShapeData(){
  return nm_visualShapeData(address());
}
