/** 
 * Returns a  {@link DoubleBuffer} view of the {@code m_appliedConstraintForces} field. 
 */
@NativeType("double[6]") public DoubleBuffer m_appliedConstraintForces(){
  return nm_appliedConstraintForces(address());
}
