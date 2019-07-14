/** 
 * Unsafe version of  {@link #m_ccdSweptSphereRadius(double) m_ccdSweptSphereRadius}. 
 */
public static void nm_ccdSweptSphereRadius(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_CCDSWEPTSPHERERADIUS,value);
}
