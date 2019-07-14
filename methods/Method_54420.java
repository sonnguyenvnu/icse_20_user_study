/** 
 * Unsafe version of  {@link #m_linkIndexB(int) m_linkIndexB}. 
 */
public static void nm_linkIndexB(long struct,int value){
  UNSAFE.putInt(null,struct + B3ContactPointData.M_LINKINDEXB,value);
}
