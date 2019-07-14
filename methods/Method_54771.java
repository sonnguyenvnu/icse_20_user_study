/** 
 * Unsafe version of  {@link #m_appliedConstraintForces(int) m_appliedConstraintForces}. 
 */
public static double nm_appliedConstraintForces(long struct,int index){
  return UNSAFE.getDouble(null,struct + B3UserConstraintState.M_APPLIEDCONSTRAINTFORCES + check(index,6) * 8);
}
