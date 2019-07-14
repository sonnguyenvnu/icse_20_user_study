/** 
 * Unsafe version of  {@link #m_relativePositionTarget}. 
 */
public static double nm_relativePositionTarget(long struct){
  return UNSAFE.getDouble(null,struct + B3UserConstraint.M_RELATIVEPOSITIONTARGET);
}
