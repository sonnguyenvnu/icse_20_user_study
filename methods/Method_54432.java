/** 
 * Unsafe version of  {@link #m_linearFrictionDirection1(int,double) m_linearFrictionDirection1}. 
 */
public static void nm_linearFrictionDirection1(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION1 + check(index,3) * 8,value);
}
