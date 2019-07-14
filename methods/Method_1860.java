/** 
 * @return true if and only if given number of degrees is allowed rotation angle, that is it isequal to 0, 90, 180 or 270
 */
public static boolean isRotationAngleAllowed(int degrees){
  return (degrees >= 0) && (degrees <= 270) && (degrees % 90 == 0);
}
