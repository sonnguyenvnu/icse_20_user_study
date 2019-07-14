/** 
 * Unsafe version of  {@link #m_orn(int,float) m_orn}. 
 */
public static void nm_orn(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3VRControllerEvent.M_ORN + check(index,4) * 4,value);
}
