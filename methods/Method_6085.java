/** 
 * Copies the rotation matrix with the greatest timestamp which is less than or equal to the given timestamp to  {@code matrix}. Removes all older rotations and the returned one from the queue. Does nothing if there is no such rotation.
 * @param matrix The rotation matrix.
 * @param timestampUs The time in microseconds to query the rotation.
 * @return Whether a rotation matrix is copied to {@code matrix}.
 */
public boolean pollRotationMatrix(float[] matrix,long timestampUs){
  float[] rotation=rotations.pollFloor(timestampUs);
  if (rotation == null) {
    return false;
  }
  getRotationMatrixFromAngleAxis(rotationMatrix,rotation);
  if (!recenterMatrixComputed) {
    computeRecenterMatrix(recenterMatrix,rotationMatrix);
    recenterMatrixComputed=true;
  }
  Matrix.multiplyMM(matrix,0,recenterMatrix,0,rotationMatrix,0);
  return true;
}
