/** 
 * Unsafe version of  {@link #m_erp}. 
 */
public static double nm_erp(long struct){
  return UNSAFE.getDouble(null,struct + B3UserConstraint.M_ERP);
}
