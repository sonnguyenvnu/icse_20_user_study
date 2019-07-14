/** 
 * Unsafe version of  {@link #m_appliedConstraintForces}. 
 */
public static DoubleBuffer nm_appliedConstraintForces(long struct){
  return memDoubleBuffer(struct + B3UserConstraintState.M_APPLIEDCONSTRAINTFORCES,6);
}
