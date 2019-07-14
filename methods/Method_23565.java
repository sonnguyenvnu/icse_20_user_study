/** 
 * ( begin auto-generated from PVector_setMag.xml ) Calculate the angle of rotation for this vector (only 2D vectors) ( end auto-generated )
 * @webref pvector:method
 * @usage web_application
 * @return the angle of rotation
 * @brief Calculate the angle of rotation for this vector
 */
public float heading(){
  float angle=(float)Math.atan2(y,x);
  return angle;
}
