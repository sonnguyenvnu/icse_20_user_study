/** 
 * Sets the specified value at the specified index of the  {@code m_localInertialFrame} field. 
 */
public B3DynamicsInfo m_localInertialFrame(int index,double value){
  nm_localInertialFrame(address(),index,value);
  return this;
}
