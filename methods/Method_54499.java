/** 
 * Unsafe version of  {@link #m_uSize}. 
 */
public static int nm_uSize(long struct){
  return UNSAFE.getInt(null,struct + B3JointInfo.M_USIZE);
}
