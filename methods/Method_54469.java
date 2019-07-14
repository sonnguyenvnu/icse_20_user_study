/** 
 * Unsafe version of  {@link #m_contactStiffness(double) m_contactStiffness}. 
 */
public static void nm_contactStiffness(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_CONTACTSTIFFNESS,value);
}
