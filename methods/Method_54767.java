/** 
 * Unsafe version of  {@link #m_erp(double) m_erp}. 
 */
public static void nm_erp(long struct,double value){
  UNSAFE.putDouble(null,struct + B3UserConstraint.M_ERP,value);
}
