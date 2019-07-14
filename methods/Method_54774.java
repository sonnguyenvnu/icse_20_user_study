/** 
 * Unsafe version of  {@link #m_appliedConstraintForces(int,double) m_appliedConstraintForces}. 
 */
public static void nm_appliedConstraintForces(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3UserConstraintState.M_APPLIEDCONSTRAINTFORCES + check(index,6) * 8,value);
}
