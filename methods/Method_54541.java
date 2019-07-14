/** 
 * Unsafe version of  {@link #m_jointMotorTorque}. 
 */
public static double nm_jointMotorTorque(long struct){
  return UNSAFE.getDouble(null,struct + B3JointSensorState2.M_JOINTMOTORTORQUE);
}
