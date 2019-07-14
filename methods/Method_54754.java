/** 
 * Unsafe version of  {@link #m_childBodyIndex(int) m_childBodyIndex}. 
 */
public static void nm_childBodyIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3UserConstraint.M_CHILDBODYINDEX,value);
}
