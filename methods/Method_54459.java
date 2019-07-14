/** 
 * Unsafe version of  {@link #m_ccdSweptSphereRadius}. 
 */
public static double nm_ccdSweptSphereRadius(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_CCDSWEPTSPHERERADIUS);
}
