/** 
 * Unsafe version of  {@link #m_linkIndex(int) m_linkIndex}. 
 */
public static void nm_linkIndex(long struct,int value){
  UNSAFE.putInt(null,struct + B3VisualShapeData.M_LINKINDEX,value);
}
