/** 
 * Unsafe version of  {@link #m_hitFraction(double) m_hitFraction}. 
 */
public static void nm_hitFraction(long struct,double value){
  UNSAFE.putDouble(null,struct + B3RayHitInfo.M_HITFRACTION,value);
}
