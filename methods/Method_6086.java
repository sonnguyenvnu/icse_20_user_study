/** 
 * Computes a recentering matrix from the given angle-axis rotation only accounting for yaw. Roll and tilt will not be compensated.
 * @param recenterMatrix The recenter matrix.
 * @param rotationMatrix The rotation matrix.
 */
public static void computeRecenterMatrix(float[] recenterMatrix,float[] rotationMatrix){
  Matrix.setIdentityM(recenterMatrix,0);
  float normRowSqr=rotationMatrix[10] * rotationMatrix[10] + rotationMatrix[8] * rotationMatrix[8];
  float normRow=(float)Math.sqrt(normRowSqr);
  recenterMatrix[0]=rotationMatrix[10] / normRow;
  recenterMatrix[2]=rotationMatrix[8] / normRow;
  recenterMatrix[8]=-rotationMatrix[8] / normRow;
  recenterMatrix[10]=rotationMatrix[10] / normRow;
}
