/** 
 * Unsafe version of  {@link #m_parentBodyIndex(int) m_parentBodyIndex}. 
 */
public static void nm_parentBodyIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3UserConstraint.M_PARENTBODYINDEX,value);
}
