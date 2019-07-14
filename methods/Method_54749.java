/** 
 * Unsafe version of  {@link #m_gearAuxLink}. 
 */
public static int nm_gearAuxLink(long struct){
  return UNSAFE.getInt(null,struct + B3UserConstraint.M_GEARAUXLINK);
}
