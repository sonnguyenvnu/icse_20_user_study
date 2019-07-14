/** 
 * Unsafe version of  {@link #m_gearRatio(double) m_gearRatio}. 
 */
public static void nm_gearRatio(long struct,double value){
  UNSAFE.putDouble(null,struct + B3UserConstraint.M_GEARRATIO,value);
}
