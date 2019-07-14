/** 
 * Returns a  {@link DoubleBuffer} view of the {@code m_positionOnBInWS} field. 
 */
@NativeType("double[3]") public DoubleBuffer m_positionOnBInWS(){
  return nm_positionOnBInWS(address());
}
