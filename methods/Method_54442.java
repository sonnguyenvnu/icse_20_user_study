/** 
 * Copies the specified  {@link DoubleBuffer} to the {@code m_localInertialDiagonal} field. 
 */
public B3DynamicsInfo m_localInertialDiagonal(@NativeType("double[3]") DoubleBuffer value){
  nm_localInertialDiagonal(address(),value);
  return this;
}
