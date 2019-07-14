/** 
 * Unsafe version of  {@link #m_pos(int,float) m_pos}. 
 */
public static void nm_pos(long struct,int index,float value){
  UNSAFE.putFloat(null,struct + B3VRControllerEvent.M_POS + check(index,4) * 4,value);
}
