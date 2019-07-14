/** 
 * Unsafe version of  {@link #m_gearRatio}. 
 */
public static double nm_gearRatio(long struct){
  return UNSAFE.getDouble(null,struct + B3UserConstraint.M_GEARRATIO);
}
