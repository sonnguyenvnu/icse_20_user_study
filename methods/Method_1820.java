/** 
 * Creates a new set of rotation options to use a specific rotation angle. <p> The rotation will be carried out in the pipeline.
 * @param angle the angle to rotate - valid values are 0, 90, 180 and 270
 */
public static RotationOptions forceRotation(@RotationAngle int angle){
  return new RotationOptions(angle,false);
}
