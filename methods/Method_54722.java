/** 
 * Unsafe version of  {@link #m_hitFraction}. 
 */
public static double nm_hitFraction(long struct){
  return UNSAFE.getDouble(null,struct + B3RayHitInfo.M_HITFRACTION);
}
