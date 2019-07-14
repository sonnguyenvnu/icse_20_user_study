/** 
 * Unsafe version of  {@link #m_gearAuxLink(int) m_gearAuxLink}. 
 */
public static void nm_gearAuxLink(long struct,int value){
  UNSAFE.putInt(null,struct + B3UserConstraint.M_GEARAUXLINK,value);
}
