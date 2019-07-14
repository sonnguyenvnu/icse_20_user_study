/** 
 * Unsafe version of  {@link #m_contactProcessingThreshold(double) m_contactProcessingThreshold}. 
 */
public static void nm_contactProcessingThreshold(long struct,double value){
  UNSAFE.putDouble(null,struct + B3DynamicsInfo.M_CONTACTPROCESSINGTHRESHOLD,value);
}
