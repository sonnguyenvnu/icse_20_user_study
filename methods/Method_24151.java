/** 
 * Record the current settings into the camera matrix, and set the matrix mode back to the current transformation matrix. <P> Note that this will destroy any settings to scale(), translate(), or whatever, because the final camera matrix will be copied (not multiplied) into the modelview.
 */
@Override public void endCamera(){
  if (!manipulatingCamera) {
    throw new RuntimeException("Cannot call endCamera() " + "without first calling beginCamera()");
  }
  camera.set(modelview);
  cameraInv.set(modelviewInv);
  manipulatingCamera=false;
}
