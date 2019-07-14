/** 
 * Unsafe version of  {@link #m_maxAppliedForce(double) m_maxAppliedForce}. 
 */
public static void nm_maxAppliedForce(long struct,double value){
  UNSAFE.putDouble(null,struct + B3UserConstraint.M_MAXAPPLIEDFORCE,value);
}
