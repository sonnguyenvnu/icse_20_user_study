/** 
 * Unsafe version of  {@link #m_linkIndexA(int) m_linkIndexA}. 
 */
public static void nm_linkIndexA(long struct,int value){
  UNSAFE.putInt(null,struct + B3ContactPointData.M_LINKINDEXA,value);
}
