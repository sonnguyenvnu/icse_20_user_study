/** 
 * Sets a rotation for a given timestamp.
 * @param timestampUs Timestamp of the rotation.
 * @param angleAxis Angle axis orientation in radians representing the rotation from cameracoordinate system to world coordinate system.
 */
public void setRotation(long timestampUs,float[] angleAxis){
  rotations.add(timestampUs,angleAxis);
}
