/** 
 * Unsafe version of  {@link #m_hitNormalWorld(int,double) m_hitNormalWorld}. 
 */
public static void nm_hitNormalWorld(long struct,int index,double value){
  UNSAFE.putDouble(null,struct + B3RayHitInfo.M_HITNORMALWORLD + check(index,3) * 8,value);
}
