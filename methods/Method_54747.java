/** 
 * Unsafe version of  {@link #m_maxAppliedForce}. 
 */
public static double nm_maxAppliedForce(long struct){
  return UNSAFE.getDouble(null,struct + B3UserConstraint.M_MAXAPPLIEDFORCE);
}
