/** 
 * Unsafe version of  {@link #m_relativePositionTarget(double) m_relativePositionTarget}. 
 */
public static void nm_relativePositionTarget(long struct,double value){
  UNSAFE.putDouble(null,struct + B3UserConstraint.M_RELATIVEPOSITIONTARGET,value);
}
