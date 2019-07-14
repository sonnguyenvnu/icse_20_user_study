/** 
 * Unsafe version of  {@link #m_frictionAnchor(int) m_frictionAnchor}. 
 */
public static void nm_frictionAnchor(long struct,int value){
  UNSAFE.putInt(null,struct + B3DynamicsInfo.M_FRICTIONANCHOR,value);
}
