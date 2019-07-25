/** 
 * Rotates this vector by the given angle in degrees around the given axis.
 * @param degrees the angle in degrees
 * @param axisX the x-component of the axis
 * @param axisY the y-component of the axis
 * @param axisZ the z-component of the axis
 * @return This vector for chaining 
 */
public Vector3 rotate(float degrees,float axisX,float axisY,float axisZ){
  return this.mul(tmpMat.setToRotation(axisX,axisY,axisZ,degrees));
}
