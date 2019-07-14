/** 
 * Unsafe version of  {@link #m_linearFrictionDirection2(int,double) m_linearFrictionDirection2}. 
 */
public static void nm_linearFrictionDirection2(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3ContactPointData.M_LINEARFRICTIONDIRECTION2 + check(index,3) * 8,value);
}
