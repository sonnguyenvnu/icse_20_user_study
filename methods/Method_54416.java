/** 
 * Unsafe version of  {@link #m_contactFlags(int) m_contactFlags}. 
 */
public static void nm_contactFlags(long struct,int value){
  UNSAFE.putInt(null,struct + B3ContactPointData.M_CONTACTFLAGS,value);
}
