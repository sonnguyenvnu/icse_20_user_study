/** 
 * Unsafe version of  {@link #m_contactProcessingThreshold}. 
 */
public static double nm_contactProcessingThreshold(long struct){
  return UNSAFE.getDouble(null,struct + B3DynamicsInfo.M_CONTACTPROCESSINGTHRESHOLD);
}
