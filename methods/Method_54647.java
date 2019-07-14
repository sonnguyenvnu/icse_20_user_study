/** 
 * Returns a  {@link DoubleBuffer} view of the {@code m_gravityAcceleration} field. 
 */
@NativeType("double[3]") public DoubleBuffer m_gravityAcceleration(){
  return nm_gravityAcceleration(address());
}
