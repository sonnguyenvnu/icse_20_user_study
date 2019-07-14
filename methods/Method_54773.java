/** 
 * Unsafe version of  {@link #m_appliedConstraintForces(DoubleBuffer) m_appliedConstraintForces}. 
 */
public static void nm_appliedConstraintForces(long struct,DoubleBuffer value){
  if (CHECKS) {
    checkGT(value,6);
  }
  memCopy(memAddress(value),struct + B3UserConstraintState.M_APPLIEDCONSTRAINTFORCES,value.remaining() * 8);
}
