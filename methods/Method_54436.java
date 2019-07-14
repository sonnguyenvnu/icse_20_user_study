/** 
 * Returns a  {@link DoubleBuffer} view of the {@code m_localInertialDiagonal} field. 
 */
@NativeType("double[3]") public DoubleBuffer m_localInertialDiagonal(){
  return nm_localInertialDiagonal(address());
}
