/** 
 * Unsafe version of  {@link #m_frictionAnchor}. 
 */
public static int nm_frictionAnchor(long struct){
  return UNSAFE.getInt(null,struct + B3DynamicsInfo.M_FRICTIONANCHOR);
}
