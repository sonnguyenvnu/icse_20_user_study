/** 
 * Unsafe version of  {@link #m_jointMotorTorque(double) m_jointMotorTorque}. 
 */
public static void nm_jointMotorTorque(long struct,double value){
  UNSAFE.putDouble(null,struct + B3JointSensorState2.M_JOINTMOTORTORQUE,value);
}
